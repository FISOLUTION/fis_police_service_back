package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ConfirmController;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.ConfirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/*
    작성 날짜: 2022/02/14 11:32 오전
    작성자: 고준영
    작성 내용: 확인서 제출(저장), 확인서 조회, 확인서 결재
*/

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConfirmControllerImpl implements ConfirmController {

    private final ConfirmService confirmService;

    // 완성
    // 현장요원이 확인서 작성하여 제출 (확인서 정보 + 현장요원 정보) 현장요원 별 하나씩
    // schedule 에 대한 확인서니까 스케쥴 아이디가 있어야겠쥬?
    @Override
    @PostMapping("/confirm/write/{schedule_id}")
    public void postConfirm(@RequestBody ConfirmFromAgentRequest formRequest, HttpServletRequest request, @PathVariable Long schedule_id) {
        Schedule schedule = confirmService.findSchedule(schedule_id);
        String date = String.valueOf(schedule.getVisit_date());
        confirmService.saveConfirm(formRequest, schedule);
    }

    // 스케쥴별 확인서 열람 (작성 안되어 있으면 오류남)
    // schedule_id 로만 가져와서 schedule 찾아서, 거기서 center_id 랑 visit_date 꺼내서 해도 됨.
    // 근데 어차피 프론트에는 schedule 에 대한 center_id 정보와 visit_time 정보가 있으니까
    @Override
    @GetMapping("/confirm/read/{schedule_id}")
    public ConfirmFormResponse confirmForCenter(HttpServletRequest request, @PathVariable Long schedule_id, @RequestParam Long center_id, @RequestParam String visit_date) {
        ConfirmFormResponse response = confirmService.showConfirm(schedule_id, center_id, visit_date);
        return response;
    }

    // /confirm/check -> 시설이 확인서에 결재 후 전송 => 확인서의 '확인' 컬럼 업데이트
    // confirm_id 는 리스트로 올 수 있도록 해야함, 시설에 여러 현장요원이 갔을 경우, 각 현장요원이 작성한 확인서의 컬ㄹ럼을 모두 업데이트 해주어야하기 때문
    // 아니면 스케쥴 아이디만 받아와서 뭐 어떻게 처리해도 되고...
    @Override
    @PostMapping("/confirm/check/{confirm_id}")
    public void updateConfirmComplete(@PathVariable Long confirm_id) {
        confirmService.updateConfirm(confirm_id);
    }
    public void updateConfirmComplete(UpdateRequest request) {
        List<Long> confirm_id = request.getConfirm_id();
        for (Long aLong : confirm_id) {
            System.out.println("aLong = " + aLong);
        }
    }

    // /confirm -> 시설용 과거 방문 이력들
    @Override
    @GetMapping("/confirm/center/{center_id}")
    public Result confirmListForCenter(HttpServletRequest request, @PathVariable Long center_id) {
        return confirmService.confirmForCenter(center_id);
    }

    // 이거 근데 /confirm/read 랑 똑같은데 왜 있는 거지??? 나중에 지워도 되는 지 확인해보자
    // /confirm/agent{schedule_id}-> 현장요원이 지난 일정에 대한 확인서 열람(하나씩)
    @Override
    @GetMapping("/confirm/agent/{schedule_id}")
    public ConfirmFormResponse confirmForAgent(@PathVariable Long schedule_id, @RequestParam Long center_id,@RequestParam String visit_date) {
        ConfirmFormResponse response = confirmService.showConfirm(schedule_id, center_id, visit_date);
        return response;
    }

    // /confirm/calendar -> 현장요원별 확인서가 제출된 날짜만 출력? 이건 아직 미정
    @Override
    @GetMapping("confirm/calendar/{agent_id}")
    public Result confirmDate(HttpServletRequest request, @PathVariable Long agent_id) {
        return confirmService.confirmForAgent(agent_id);
    }
}

// 와 만약에, 3명이 한 시설에 같은 날짜, 시간에 가면 schedule_id 가 3개 생성된다. (현장요원 별로)
// 그러면 웹 사이트에서 관리자가 체크 리스트로 시설과 요원한테 공지가 뽝뽝뽝 갈텐데
// 현장요원은 각자 받으면 되니까 상관 없는데
// 만약에 시설에는 알림이 3번 간다면.? => 그래서 schedule 공지에는 이걸 막을 메서드가 필요하다.
// 그러면 시설에 공지완료 컬럼이 있어야 하나??
// 일단 이미 첫번 째 현장요원한테 공지할 때, 시설에도 공지가 가
// 근데 현장요원이 한 두명 추가되어서 얘네한테도 공지를 보낼 때, 또 시설에도 공지를 보내면 안됨
// 그러면 현장요원과 시설에 공지를 보낼 때, 스케쥴에는 한번 거르는 작업이 있어야 할 것 같은데
// 디비에 시설아이디, visit_date, visit_time 값을 넣어서 조회했을 때 자기보다 작은 schedule_id 가 있다면 시설에는 공지하지 않는 걸로!!
