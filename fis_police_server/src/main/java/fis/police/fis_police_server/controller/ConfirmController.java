package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    작성 날짜: 2022/02/14 11:32 오전
    작성자: 고준영
    작성 내용: 확인서 제출(저장), 확인서 조회, 확인서 결재
*/
public interface ConfirmController {
    // 현장요원이 확인서 작성하여 제출(인원에 대한 정보만 작성하여 제출하면 됨)
    // schedule_id 에서 agent, center, 방문날짜에 대한 저보 모두 열람 가능 (schedule 에는 날짜와 시간이 local 로 박혀있어, 우선은 프론트에서 복사하여 보내도록 설정해놓았음)
    void postConfirm(ConfirmFromAgentRequest formRequest, HttpServletRequest request, @PathVariable Long schedule_id);

    // /confirm/read/?center_id={}&date={} -> 시설용 확인서 열람 결재 미완료된 확인서! => 아래 메서드로 결재 요청이 오면 complete update 하기
    ConfirmFormResponse confirmForCenter(HttpServletRequest request, @PathVariable Long schedule_id, @RequestParam Long center_id, @RequestParam String visit_date);

    // /confirm/check -> 시설이 확인서에 결재 후 전송 => 확인서의 '확인' 컬럼 업데이트
    void updateConfirmComplete(@RequestBody UpdateRequest request); // param 을 따로 dto 로 묶을 필요가 있음

    // /confirm -> 시설용 과거 방문 이력들
    Result confirmListForCenter(HttpServletRequest request, @PathVariable Long center_id);  // request 에서 로그인한 사용자 정보(시설 담당자 id) 꺼내와서 그 사람의 시설 id로 confirm list 찾기


    // /confirm/agent{schedule_id}-> 현장요원이 지난 일정에 대한 확인서 열람(하나씩) 결재 완료된 확인서!
    ConfirmFormResponse confirmForAgent(@PathVariable Long schedule_id, @RequestParam Long center_id, @RequestParam String visit_date);

    // /confirm/calendar -> 현장요원별 확인서가 제출된 날짜만 출력? 이건 아직 미정
    Result confirmDate(HttpServletRequest request, @PathVariable Long agent_id);

}
