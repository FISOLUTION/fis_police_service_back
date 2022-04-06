package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.CalendarController;
import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.domain.Calendar;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.CalendarDeleteRequest;
import fis.police.fis_police_server.dto.CalendarListDTO;
import fis.police.fis_police_server.dto.CalendarModifyRequest;
import fis.police.fis_police_server.dto.CalendarSaveRequest;
import fis.police.fis_police_server.service.interfaces.CalendarService;
import fis.police.fis_police_server.service.interfaces.OfficialService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 날짜 : 2022/04/06 11:02 오전
 * 작성자 : 원보라
 * 작성내용 : 일정표
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class CalendarControllerImpl implements CalendarController {
    private final CalendarService calendarService;
    private final TokenService tokenService;
    private final OfficialService officialService;


    /**
     * 일정 추가
     *
     * @param calendarSaveRequest
     * @param httpServletRequest
     * @param response
     * @throws IOException
     */
    @Override
    @PostMapping("/calendar")
    public void saveCalendar(@RequestBody CalendarSaveRequest calendarSaveRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        Officials findOfficial = officialService.findById(officialFromRequest.getId());
        log.info("[로그인 id값: {}] [url: {}] [요청: 일정표 일정 추가]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/calendar");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            calendarService.saveCalendar(findOfficial, calendarSaveRequest);            //성공시 200 ok
            log.info("일정표 일정 추가 완료");
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoCalendar");
        }
    }

    /**
     * 일정 수정
     *
     * @param calendarModifyRequest
     * @param httpServletRequest
     * @param response
     * @throws IOException
     */
    @Override
    @PatchMapping("/calendar")
    public void modifyCalendar(@RequestBody CalendarModifyRequest calendarModifyRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        log.info("[로그인 id값: {}] [url: {}] [요청: 일정표 일정 수정]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/calendar");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            Calendar calendar = calendarService.findById(calendarModifyRequest.getCalendar_id());
            if (officialFromRequest.getId().equals(calendar.getOfficials().getId())) { //일정을 작성한 official 맞을 경우 수정 가능
                calendarService.modifyCalendar(calendarModifyRequest);            //성공시 200 ok
                log.info("일정표 일정 수정 완료");
            } else {
                throw new IllegalArgumentException("DifferentOfficial"); //다른 사람이 수정하려 할 때
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoCalendar");
        }
    }

    /**
     * 일정 삭제
     *
     * @param calendarDeleteRequest
     * @param httpServletRequest
     * @param response
     * @throws IOException
     */
    @Override
    @PatchMapping("/calendar/delete")
    public void deleteCalendar(@RequestBody CalendarDeleteRequest calendarDeleteRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        log.info("[로그인 id값: {}] [url: {}] [요청: 일정표 일정 삭제]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/calendar/delete");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            Calendar calendar = calendarService.findById(calendarDeleteRequest.getCalendar_id());
            if (officialFromRequest.getId().equals(calendar.getOfficials().getId())) { //일정을 작성한 official 맞을 경우 수정 가능
                calendarService.deleteCalendar(calendarDeleteRequest);            //성공시 200 ok
                log.info("일정표 일정 삭제 업데이트");
            } else {
                throw new IllegalArgumentException("DifferentOfficial"); //다른 사람이 삭제하려 할 때
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoCalendar");
        }
    }

    //todo 모든 일정을 보내주는데 달별로 보내줘야할까?
    //todo 나중에 데이터 많이 쌓이면 쓸데없이 과거 일정을 많이 보내주는거 아닐까?
    /**
     * 일정 모두 조회
     * 삭제된 일정은 안보임
     *
     * @param httpServletRequest
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    @GetMapping("/calendar")
    public List<CalendarListDTO> getCalendar(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        try {
            return calendarService.findAll();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        }
    }
}
