package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.ScheduleModifyRequest;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;
import fis.police.fis_police_server.dto.ScheduleByDateResponse;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.ScheduleRepository;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/*
    작성날짜: 2022/01/12 4:42 PM
    작성자: 이승범
    작성내용: ScheduleServiceImpl 생성
*/
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CenterRepository centerRepository;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;

    @Override
    @Transactional
    public void assignAgent(ScheduleSaveRequest request) throws Exception {
        Center findCenter =  centerRepository.findById(request.getCenter_id());
        User findUser = userRepository.findById(request.getUser_id());
        Agent findAgent = agentRepository.findById(request.getAgent_id());
        Schedule schedule = Schedule.createSchedule(findCenter, findUser, findAgent, request.getReceipt_date(),
                request.getVisit_date(), request.getVisit_time(), request.getEstimate_num(),
                request.getCenter_etc(), request.getAgent_etc());
        scheduleRepository.save(schedule);
    }
/*
    작성날짜: 2022/01/13 5:54 PM
    작성자: 이승범
    작성내용: 날짜별 현장요원 스케줄 가져오기 구현
*/
    @Override
    public List<ScheduleByDateResponse> selectDate(LocalDate date) {
        return scheduleRepository.findAllByDate(date);
    }
/*
    작성날짜: 2022/01/14 4:40 PM
    작성자: 이승범
    작성내용: modifySchedule 작성
*/
    @Override
    @Transactional
    public void modifySchedule(ScheduleModifyRequest request) throws NullPointerException{
        Schedule findSchedule = scheduleRepository.findById(request.getSchedule_id());
        List<Agent> findAgentList = agentRepository.findByA_code(request.getA_code());
        // 해당하는 현장요원이 존재하는지 검사
        if(findAgentList.isEmpty()){
            throw new NullPointerException();
        }
        Agent findAgent = findAgentList.get(0);
        Center findCenter = centerRepository.findById(request.getCenter_id());
        findSchedule.modifySchedule(request, findAgent, findCenter);
    }
}
