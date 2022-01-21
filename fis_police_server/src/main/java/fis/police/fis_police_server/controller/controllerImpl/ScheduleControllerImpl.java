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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;

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
    public void assignAgent(@RequestBody ScheduleSaveRequest request, HttpServletRequest httpServletRequest) {
        try{
            HttpSession session = httpServletRequest.getSession();
            Long userId = (Long) session.getAttribute("loginUser");
            scheduleService.assignAgent(request, userId);
        } catch (Exception e){
            System.out.println(e);
        }
    }
/*
    작성날짜: 2022/01/13 1:44 PM
    작성자: 이승범
    작성내용: selectDate 작성 날짜별 스케줄
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
    /*
        작성날짜: 2022/01/14 4:33 PM
        작성자: 이승범
        작성내용: 스케줄 수정
    */
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

    /*
        작성날짜: 2022/01/19 4:33 PM
        작성자: 이승범
        작성내용: 스케줄 취소 구현
    */
    @Override
    @GetMapping("/schedule/cancel")
    public void cancelSchedule(@RequestParam("schedule_id") Long schedule_id) {
        try{
            scheduleService.cancelSchedule(schedule_id);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
