package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.domain.enumType.Complete;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.*;
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
    private final OfficialsRepository officialsRepository;

    // 확인서 저장
    @Override
    public void saveConfirm(ConfirmFromAgentRequest request, Schedule schedule) {
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
            response.getAgent_name().add(confirm.getAgent().getA_name());
            response.getConfirm_id().add(confirm.getId());
        }

        return response;
    }

    // 확인서 결재하기
    @Override
    public void updateConfirm(Long confirm_id, String name) {
        Complete complete = Complete.complete;
        confirmRepository.updateConfirmComplete(confirm_id, complete, name);
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

    // 해당 스케쥴에 대한 확인서 열람 (시설, 현장요원 모두)
    @Override
    public ConfirmFormResponse showConfirm(Center center, String visit_date) {
        List<Confirm> sameCenterDate = confirmRepository.findSameCenterDate(center, visit_date);
        return combineConfirm(sameCenterDate);
    }

    @Override
    public Result confirmForAgent(Agent agent) {
        Complete complete = Complete.complete;
        List<Confirm> completeConfirmListForAgent = confirmRepository.findCompleteConfirmListForAgent(complete, agent);
        List<CalendarDTO> collect = completeConfirmListForAgent.stream()
                .map(confirm -> new CalendarDTO(confirm.getAgent().getA_name(), confirm.getCenter().getC_name(),
                        confirm.getVisit_date(), confirm.getComplete()))
                .collect(Collectors.toList());
        return new Result(collect);
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

    @Override
    public Officials findOfficial(Long id) {
        return officialsRepository.findById(id);
    }
}