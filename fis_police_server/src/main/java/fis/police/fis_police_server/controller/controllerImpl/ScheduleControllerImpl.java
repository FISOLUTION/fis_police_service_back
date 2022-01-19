package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ScheduleController;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.dto.ScheduleModifyRequest;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;
import fis.police.fis_police_server.service.AnnounceService;
import fis.police.fis_police_server.service.ScheduleService;
import fis.police.fis_police_server.service.serviceImpl.MapConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public void assignAgent(@RequestBody ScheduleSaveRequest request) {
        try{
            scheduleService.assignAgent(request);
        } catch (Exception e){
            System.out.println(e);
        }
    }
/*
    작성날짜: 2022/01/13 1:44 PM
    작성자: 이승범
    작성내용: selectDate 작성 날짜별 현장요원 리스트
*/
    @Override
    @GetMapping("/schedule")
    public Result selectDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try{
            return new Result(scheduleService.selectDate(date));
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    @PatchMapping("/schedule")
    public void modifySchedule(@RequestBody ScheduleModifyRequest request) {
        try{
            scheduleService.modifySchedule(request);
        } catch (NullPointerException ne){
            System.out.println("존재하지 않는 요원 코드입니다.");
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
