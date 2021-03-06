package fis.police.fis_police_server.service.serviceImpl;

import com.mysema.commons.lang.Pair;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.interfaces.AgentRepository;
import fis.police.fis_police_server.repository.interfaces.ScheduleRepository;
import fis.police.fis_police_server.service.interfaces.AgentService;
import fis.police.fis_police_server.service.interfaces.MapService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.AddressException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    작성날짜: 2022/01/10 5:41 PM
    작성자: 이승범
    작성내용: AgentServiceImpl 구현중
*/
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final MapService mapService;
    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional // 현장요원 추가
    public Agent saveAgent(AgentSaveRequest request) throws AddressException, ParseException {

        validateDuplicateAgent(request.getA_code()); // 현장요원 코드 중복 검사
        checkDuplicateByNickname(request.getNickname());

        Pair<Double, Double> pair = mapService.addressToLocation(request.getA_address());
        HasCar hasCar = request.isA_hasCar() ? HasCar.CAR : HasCar.WALK;

        Agent agent = Agent.createAgent(request, hasCar, pair.getFirst(), pair.getSecond());

        agentRepository.save(agent);
        return agent;

    }

    @Override
    @Transactional // 현장요원 수정
    // 수정사항 없어도 update 쿼리 나가는 이슈있음
    public Agent modifyAgent(AgentModifyRequest request) throws AddressException, ParseException {

        Agent findAgent = agentRepository.findById(request.getAgent_id());

        // 현장요원 코드가 달라졌으면 중복검사
        if (!findAgent.getA_code().equals(request.getA_code())) {
            validateDuplicateAgent(request.getA_code());
        }
        //닉네임과 비밀번호 처음부터 없을 경우 오류 남 --> 비어있을때는 중복검사 안함
        if (findAgent.getA_nickname() != null && !findAgent.getA_nickname().equals(request.getNickname())) {
            checkDuplicateByNickname(request.getNickname());
        }

        HasCar hasCar = request.isA_hasCar() ? HasCar.CAR : HasCar.WALK;
        AgentStatus agentStatus = request.isA_status() ? AgentStatus.WORK : AgentStatus.FIRED;

        // 현장요원 주소가 바뀐 경우
        if (!request.getA_address().equals(findAgent.getA_address())) {
            Pair<Double, Double> pair = mapService.addressToLocation(request.getA_address());
            findAgent.modifyAgent(request, hasCar, pair.getFirst(), pair.getSecond(), agentStatus);
        } else { // 현장요원 주소는 안바뀐 경우
            findAgent.modifyAgent(request, hasCar, findAgent.getA_longitude(), findAgent.getA_latitude(), agentStatus);
        }
        return findAgent;
    }

    @Override
    public Agent findById(Long id) {
        try {
            return agentRepository.findById(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("해당 현장요원 정보 없음.");
        }
    }

    @Override // 전체 현장요원 조회
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

    // 현장요원 코드 중복 검사
    private void validateDuplicateAgent(String a_code) {
        List<Agent> findAgentList = agentRepository.findByA_code(a_code);
        if (!findAgentList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 현장요원 코드입니다.");
        }
    }
    // 현장요원 nickname(id) 중복 검사
    private void checkDuplicateByNickname(String nickname) {
        List<Agent> byNickname = agentRepository.findByNickname(nickname);
        if (!byNickname.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }


    /*
        날짜 : 2022/02/15 1:37 오후
        작성자 : 원보라
        작성내용 : 현장요원 사진 추가
    */
    @Value("${profileImg.path}")
    private String uploadFolder;

    @Override
    @Transactional
    public void updatePicture(Long Agent_id, MultipartFile multipartFile) {
        Agent agent = agentRepository.findById(Agent_id);
//        if (multipartFile.isEmpty()) {
//            deletePicture(agent.getId());
//        }
        String originalFileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String imageFileName = agent.getId() + "." + originalFileExtension;
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        if (multipartFile.getSize() != 0) { //파일이 업로드 되었는지 확인
            try {
                if (agent.getA_picture() != null) { // 이미 프로필 사진이 있을경우
                    File file = new File(uploadFolder + agent.getA_picture()); // 경로 + 유저 프로필사진 이름을 가져와서
                    file.delete(); // 원래파일 삭제
                }
                Files.createDirectories(Paths.get(uploadFolder));//이미 존재하는 경우 directory 새로 안만듦 , 상위 directory 없는 경우 생성
                Files.write(imageFilePath, multipartFile.getBytes());
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            agent.uploadPicture(imageFileName);
        }
    }

    @Override
    @Transactional
    public String getPicture(Long agent_id) {
        Agent agent = agentRepository.findById(agent_id);
        return agent.getA_picture();
    }

    @Override
    @Transactional
    public void deletePicture(Long agent_id) {
        Agent agent = agentRepository.findById(agent_id);
        //진짜 사진 파일을 삭제
        if (agent.getA_picture() != null) { // 이미 프로필 사진이 있을경우
            File file = new File(uploadFolder + agent.getA_picture()); // 경로 + 유저 프로필사진 이름을 가져와서
            file.delete(); // 원래파일 삭제
        }
        //디비에 사진 이름 삭제
        agentRepository.deletePicture(agent_id);
    }

    @Override
    @Transactional
    public void saveCurrentLocation(Long agent_id, AgentLocation agentLocation) {
        Agent agent = agentRepository.findById(agent_id);
        agent.saveCurLocation(agentLocation.getA_cur_lat(), agentLocation.getA_cur_long());
    }

    @Override
    public List<AgentByMonthDTO> searchByMonth(String month, String keyword) {
        List<AgentByMonthDTO> result = new ArrayList<>();

        Map<Agent, List<Schedule>> scheduleMap = scheduleRepository.findByAgentsAndMonth(keyword, month)
                .stream().collect(Collectors.groupingBy(Schedule::getAgent));

        scheduleMap.forEach((a, s) -> {
            AgentByMonthDTO dto = new AgentByMonthDTO(a);
            Map<LocalDate, List<Schedule>> scheduleDateMap = s.stream().collect(Collectors.groupingBy(Schedule::getVisit_date));
            scheduleDateMap.forEach((d, sc) -> {
                List<AgentByMonthDTO.ScheduleDTO> scheduleDTOS = sc.stream().map(AgentByMonthDTO.ScheduleDTO::new).collect(Collectors.toList());
                AgentByMonthDTO.ScheduleByDTO scheduleByDTO = new AgentByMonthDTO.ScheduleByDTO(d, scheduleDTOS);
                dto.getScheduleList().add(scheduleByDTO);
            });
            result.add(dto);
        });

        return result;
    }
}
