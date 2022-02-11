package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.ConfirmFormResponse;
import fis.police.fis_police_server.dto.ConfirmFromAgentRequest;
import fis.police.fis_police_server.dto.ConfirmListForCenterResponse;
import fis.police.fis_police_server.dto.ConfirmUpdateCompleteResponse;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 고준앱
public interface ConfirmController {
    // /confirm -> 시설용 과거 방문 이력들
    ConfirmListForCenterResponse confirmListForCenter(HttpServletRequest request);  // request 에서 로그인한 사용자 정보(시설 담당자 id) 꺼내와서 그 사람의 시설 id로 confirm list 찾기

    // /confirm/read/?center_id={}&date={} -> 시설용 확인서 열람 결재 미완료된 확인서! => 아래 메서드로 결재 요청이 오면 complete update 하기
    ConfirmFormResponse confirmForCenter(HttpServletRequest request, @PathVariable Long schedule_id);
    // 얘도 schedule_id 를 넘겨줄 수 있는 거 아닌가??

    // /confirm/check -> 시설이 확인서에 결재 후 전송 => 확인서의 '확인' 컬럼 업데이트
    ConfirmUpdateCompleteResponse updateConfirmComplete(List<Long> confirm_id); // param 을 따로 dto 로 묶을 필요가 있음

    // /confirm/write -> 현장요원이 확인서 작성하여 제출 (확인서 정보 + 현장요원 정보) 현장요원 별 하나씩
    void postConfirm(ConfirmFromAgentRequest formRequest, HttpServletRequest request);

    // /confirm/agent{schedule_id}-> 현장요원이 지난 일정에 대한 확인서 열람(하나씩) 결재 완료된 확인서!
    ConfirmFormResponse confirmForAgent(@PathVariable Long schedule_id);
    // 이건 confirmForCenter 랑 뭔가 합쳐져도 될 것 같은 너낌스...~?

    // /confirm/calendar -> 현장요원별 확인서가 제출된 날짜만 출력? 이건 아직 미정
    void confirmDate();

}
