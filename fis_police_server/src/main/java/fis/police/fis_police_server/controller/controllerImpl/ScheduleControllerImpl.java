package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ScheduleController;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.dto.ScheduleModifyRequest;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;
import fis.police.fis_police_server.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/*
    작성날짜: 2022/01/12 4:20 PM
    작성자: 이승범
    작성내용: ScheduleControllerImpl 생성
*/
@RestController
@RequiredArgsConstructor
@Slf4j
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;
/*
    작성날짜: 2022/01/13 1:40 PM
    작성자: 이승범
    작성내용: assignAgent 작성
*/
    @Override
    @PostMapping("/schedule")// 현장요원 배치
    public void assignAgent(
            @RequestBody ScheduleSaveRequest request, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        try{
            HttpSession session = httpServletRequest.getSession();
            Long userId = (Long) session.getAttribute("loginUser");
            scheduleService.assignAgent(request, userId);
        } catch (NullPointerException npe){
            log.warn("[로그인 id값 : {}] [url: {}] [식별되지않는 시설 or 현장요원 or 콜직원 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/schedule", npe.getMessage());
            response.setStatus(400);
        }catch (Exception e){
            log.error("[로그인 id값 : {}] [url: {}] [예상치못한 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/schedule", e.getMessage());
            response.setStatus(500);
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
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try{
            return new Result(scheduleService.selectDate(date));
        } catch (Exception e){
            log.error("[로그인 id값 : {}] [url: {}] [예상치못한 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/schedule", e.getMessage());
            response.setStatus(500);
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
    public void modifySchedule(@RequestBody ScheduleModifyRequest request, HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try{
            scheduleService.modifySchedule(request);
        } catch (NullPointerException ne){
            log.warn("[로그인 id 값 : {}] [url: {}] [존재하지 않는 코드 or id입니다. {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/schedule", ne.getMessage());
            response.setStatus(400);
        } catch (Exception e){
            log.error("[로그인 id값 : {}] [url: {}] [예상치못한 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/schedule", e.getMessage());
            response.setStatus(500);
        }
    }

    /*
        작성날짜: 2022/01/19 4:33 PM
        작성자: 이승범
        작성내용: 스케줄 취소 구현
    */
    @Override
    @GetMapping("/schedule/cancel")
    public void cancelSchedule(@RequestParam("schedule_id") Long schedule_id, HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try{
            scheduleService.cancelSchedule(schedule_id);
        } catch(NullPointerException npe){
            log.warn("[로그인 id값 : {}] [url: {}] [존재하지않는 스케쥴 id {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/schedule/cancel", npe.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e){
            log.error("[로그인 id값 : {}] [url: {}] [예상치못한 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/schedule/cancel", e.getMessage());
            response.setStatus(500);
        }
    }
}
