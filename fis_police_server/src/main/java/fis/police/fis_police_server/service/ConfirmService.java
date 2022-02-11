package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Confirm;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.dto.ConfirmFormResponse;
import fis.police.fis_police_server.dto.ConfirmFromAgentRequest;
import fis.police.fis_police_server.dto.ConfirmListForCenterResponse;
import fis.police.fis_police_server.dto.Result;

import java.util.List;

public interface ConfirmService {

    // 확인서 저장
    void saveConfirm(ConfirmFromAgentRequest request, Schedule schedule);

    // 확인서 하나로 묶기
    ConfirmFormResponse combineConfirm(List<Confirm> dupleList);

    // 확인서 결재하기
    void updateConfirm(Long confirm_id);

    // [방문이력 조회] 시설별 확인서 조회 (모두)
    Result confirmForCenter(Long center_id);

    // 해당 스케쥴에 대한 확인서 열람 (시설, 현장요원 모두)
    ConfirmFormResponse showConfirm(Long schedule_id, Long center_id, String visit_date);

    // [방문이력 조회] 요원별 확인서 조회 (모두)
    Result confirmForAgent(Long agent_id);

    // 시설, 현장요원, 스케쥴 찾기
    Agent findAgent(Long id);
    Center findCenter(Long id);
    Schedule findSchedule(Long id);

}
