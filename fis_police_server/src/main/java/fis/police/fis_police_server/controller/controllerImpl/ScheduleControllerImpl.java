package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ScheduleController;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.dto.ScheduleGetResponse;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;
import fis.police.fis_police_server.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/*
    작성날짜: 2022/01/12 4:20 PM
    작성자: 이승범
    작성내용: ScheduleControllerImpl 생성
*/
@RestController
@RequiredArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;
/*
    작성날짜: 2022/01/13 1:40 PM
    작성자: 이승범
    작성내용: assignAgent 작성
*/
    @Override
    @PostMapping("/schedule")// 현장요원 배치
    public Boolean assignAgent(@RequestBody ScheduleSaveRequest request) {
        try{
            return scheduleService.assignAgent(request);
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
/*
    작성날짜: 2022/01/13 1:44 PM
    작성자: 이승범
    작성내용: selectDate 작성 날짜별 현장요원 리스트
*/
    @Override
    @GetMapping("/schedule")
    public List<ScheduleGetResponse> selectDate(@RequestParam LocalDate date) {
        try{
            List<Schedule> scheduleList = scheduleService.selectDate(date);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Boolean modifySchedule() {
        return null;
    }

    @Override
    public Boolean announceSchedule() {
        return null;
    }
}
