package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Confirm;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.enumType.Complete;
import fis.police.fis_police_server.dto.ConfirmFormResponse;
import fis.police.fis_police_server.dto.ConfirmFromAgentRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.ConfirmRepository;
import fis.police.fis_police_server.repository.ScheduleRepository;
import fis.police.fis_police_server.service.ConfirmService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmServiceImpl implements ConfirmService {

    private final ConfirmRepository confirmRepository;
    private final CenterRepository centerRepository;
    private final AgentRepository agentRepository;
    private final ScheduleRepository scheduleRepository;

    // 확인서 저장
    @Override
    public void saveConfirm(ConfirmFromAgentRequest request, Schedule schedule) {
        Agent agent = schedule.getAgent();
        Center center = schedule.getCenter();
        Confirm confirm = Confirm.createConfirm(request, schedule);
        confirmRepository.save(confirm);
    }

    // 확인서 하나로 묶기
    @Override
    public ConfirmFormResponse combineConfirm(List<Confirm> dupleList) {
        ConfirmFormResponse response = new ConfirmFormResponse();

        // 따로 메서드 파는 걸 추천함... 코드가 너무 지저분;
        response.setCenter_name(dupleList.get(0).getCenter().getC_name());
        response.setCenter_address(dupleList.get(0).getCenter().getC_address());
        response.setCenter_ph(dupleList.get(0).getCenter().getC_ph());
        response.setVisit_date(dupleList.get(0).getVisit_date());
        response.setVisit_time(dupleList.get(0).getVisit_time());
        response.setNew_child(dupleList.get(0).getNew_child());
        response.setOld_child(dupleList.get(0).getOld_child());
        response.setSenile(dupleList.get(0).getSenile());
        response.setDisabled(dupleList.get(0).getDisabled());
        response.setEtc(dupleList.get(0).getEtc());
        response.setComplete(dupleList.get(0).getComplete());

        for (Confirm confirm : dupleList) {
            System.out.println("confirm.getAgent().getA_name() = " + confirm.getAgent().getA_name());
            response.getAgent_name().add(confirm.getAgent().getA_name());
            response.getConfirm_id().add(confirm.getId());
//            response.getAgent_name().add(confirm.getAgent().getA_name());
        }

        return response;
    }

    // 확인서 결재하기
    @Override
    public void updateConfirm(Long confirm_id) {
        Complete complete = Complete.complete;
        confirmRepository.updateConfirmComplete(confirm_id, complete);
    }

    // [방문이력 조회] 시설별 확인서 조회 (모두)
    @Override
    public Result confirmForCenter(Long center_id) {
        Center center = findCenter(center_id);
        List<Confirm> completeConfirmListForCenter = confirmRepository.findCompleteConfirmListForCenter(center);
        List<ConfirmDTO> collect = completeConfirmListForCenter.stream()
                .map(confirm -> new ConfirmDTO(confirm.getId(), confirm.getVisit_date(), confirm.getVisit_time(), confirm.getCenter().getC_name(),
                        confirm.getAgent().getA_name(), confirm.getNew_child(), confirm.getOld_child(), confirm.getSenile(), confirm.getDisabled(),
                        confirm.getEtc(), confirm.getComplete()))
                .collect(Collectors.toList());
        return new Result(collect);
    }
    @Data
    @AllArgsConstructor
    static class ConfirmDTO {
        private Long confirm_id;
        private String visit_date;
        private String visit_time;
        private String center_name;
        private String agent_name;  // list 로 바꿔야함.
        private String new_child;
        private String old_child;   // 기존 아동

        private String senile;  // 치매 환자
        private String disabled;    // 지적장애
        private String etc;
        private Complete complete;  // 시설이 확인했는지 여부
    }

    // 해당 스케쥴에 대한 확인서 열람 (시설, 현장요원 모두)
    @Override
    public ConfirmFormResponse showConfirm(Long schedule_id, Long center_id, String visit_date) {
        Center center = findCenter(center_id);
        List<Confirm> sameCenterDate = confirmRepository.findSameCenterDate(center, visit_date);

        return combineConfirm(sameCenterDate);
//        return substitution(sameCenterDate);
    }
    private ConfirmFormResponse showConfirmV2(Long schedule_id) {
        Schedule schedule = findSchedule(schedule_id);
        Center center = schedule.getCenter();
        String visit_date = String.valueOf(schedule.getVisit_date());
        List<Confirm> sameCenterDate = confirmRepository.findSameCenterDate(center, visit_date);

//        return combineConfirm(sameCenterDate);
        return substitution(sameCenterDate);
    }

    private ConfirmFormResponse substitution(List<Confirm> dupleList) {

        ConfirmFormResponse response = new ConfirmFormResponse();

        response.setCenter_name(dupleList.get(0).getCenter().getC_name());
        response.setCenter_address(dupleList.get(0).getCenter().getC_address());
        response.setCenter_ph(dupleList.get(0).getCenter().getC_ph());
        response.setVisit_date(dupleList.get(0).getVisit_date());
        response.setVisit_time(dupleList.get(0).getVisit_time());
        response.setNew_child(dupleList.get(0).getNew_child());
        response.setOld_child(dupleList.get(0).getOld_child());
        response.setSenile(dupleList.get(0).getSenile());
        response.setDisabled(dupleList.get(0).getDisabled());
        response.setEtc(dupleList.get(0).getEtc());
        response.setComplete(dupleList.get(0).getComplete());
//        response.getAgent_name().add(dupleList.get(0).getAgent().getA_name());

        return response;
    }

    @Override
    public Result confirmForAgent(Long agent_id) {
        Agent agent = findAgent(agent_id);
        Complete complete = Complete.complete;
        List<Confirm> completeConfirmListForAgent = confirmRepository.findCompleteConfirmListForAgent(complete, agent);
        List<calendarDTO> collect = completeConfirmListForAgent.stream()
                .map(confirm -> new calendarDTO(confirm.getAgent().getA_name(), confirm.getCenter().getC_name(),
                        confirm.getVisit_date(), confirm.getComplete()))
                .collect(Collectors.toList());
        return new Result(collect);
    }
    @Data
    @AllArgsConstructor
    static class calendarDTO {
        private String agent_name;  // 현장요원 정보를 주는 것, 다른 내용을 줘도 됨
        private String center_name; // 시설 정보를 주는 것, 다른 내용을 줘도 됨
        private String visit_date;
        private Complete complete;
    }

    @Override
    public Agent findAgent(Long id) {
        return agentRepository.findById(id);
    }

    @Override
    public Center findCenter(Long id) {
        return centerRepository.findById(id);
    }

    @Override
    public Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id);
    }
}
