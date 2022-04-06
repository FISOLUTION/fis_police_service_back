package fis.police.fis_police_server.service.interfaces;

import fis.police.fis_police_server.domain.Calendar;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.CalendarDeleteRequest;
import fis.police.fis_police_server.dto.CalendarListDTO;
import fis.police.fis_police_server.dto.CalendarModifyRequest;
import fis.police.fis_police_server.dto.CalendarSaveRequest;

import java.util.List;

/**
 * 날짜 : 2022/04/06 10:49 오전
 * 작성자 : 원보라
 * 작성내용 : 일정표
 */
public interface CalendarService {
    //알림장 게시물 추가
    Calendar saveCalendar(Officials officials, CalendarSaveRequest calendarSaveRequest);

    //알림장 게시물 수정
    Calendar modifyCalendar(CalendarModifyRequest calendarModifyRequest);

    //알림장 게시물 삭제
    Calendar deleteCalendar(CalendarDeleteRequest calendarDeleteRequest);

    //알림장 하나 조회
    Calendar findById(Long id);

    //모든 일정 조회
    List<CalendarListDTO> findAll();
}
