package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.domain.Calendar;
import fis.police.fis_police_server.dto.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *    날짜 : 2022/04/06 11:02 오전
 *    작성자 : 원보라
 *    작성내용 : 일정표
 */
public interface CalendarController {
    //게시글 추가
    void saveCalendar(CalendarSaveRequest calendarSaveRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;


    //게시글 수정
    void modifyCalendar(CalendarModifyRequest calendarModifyRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;


    //게시글 삭제 컬럼 업데이트
    void deleteCalendar(CalendarDeleteRequest calendarDeleteRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;


    //조회
    List<CalendarListDTO> getCalendar(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;
}
