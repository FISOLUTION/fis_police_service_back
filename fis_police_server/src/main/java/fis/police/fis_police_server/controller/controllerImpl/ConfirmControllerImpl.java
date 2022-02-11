package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ConfirmController;
import fis.police.fis_police_server.dto.ConfirmFormResponse;
import fis.police.fis_police_server.dto.ConfirmFromAgentRequest;
import fis.police.fis_police_server.dto.ConfirmListForCenterResponse;
import fis.police.fis_police_server.dto.ConfirmUpdateCompleteResponse;
import fis.police.fis_police_server.service.ConfirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConfirmControllerImpl implements ConfirmController {

    private final ConfirmService confirmService;

    // /confirm -> 시설용 과거 방문 이력들
    @Override
    @GetMapping("/confirm")
    public ConfirmListForCenterResponse confirmListForCenter(HttpServletRequest request) {
        return null;
    }

    // /confirm/read?schedule_id={} -> 시설용 확인서 열람 결재 미완료된 확인서! => 아래 메서드로 결재 요청이 오면 complete update 하기
    @Override
    @GetMapping("/confirm/read/{schedule_id}")
    public ConfirmFormResponse confirmForCenter(HttpServletRequest request, @PathVariable Long schedule_id) {
        return null;
    }

    // /confirm/check -> 시설이 확인서에 결재 후 전송 => 확인서의 '확인' 컬럼 업데이트
    @Override
    public ConfirmUpdateCompleteResponse updateConfirmComplete(List<Long> confirm_id) {
        return null;
    }

    // /confirm/write -> 현장요원이 확인서 작성하여 제출 (확인서 정보 + 현장요원 정보) 현장요원 별 하나씩
    @Override
    public void postConfirm(ConfirmFromAgentRequest formRequest, HttpServletRequest request) {

    }

    // /confirm/agent?schedule_id={} -> 현장요원이 지난 일정에 대한 확인서 열람(하나씩) 결재 완료된 확인서!
    @Override
    public ConfirmFormResponse confirmForAgent(@PathVariable Long schedule_id) {
        return null;
    }

    // /confirm/calendar -> 현장요원별 확인서가 제출된 날짜만 출력? 이건 아직 미정
    @Override
    public void confirmDate() {

    }
}
