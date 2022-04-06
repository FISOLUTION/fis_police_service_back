package fis.police.fis_police_server.repository.interfaces;

import fis.police.fis_police_server.domain.Calendar;
import fis.police.fis_police_server.dto.CalendarListDTO;

import java.util.List;

/**
 *    날짜 : 2022/04/06 10:24 오전
 *    작성자 : 원보라
 *    작성내용 : 일정표
 */
public interface CalendarRepository {
    //일정 저장
    void save(Calendar calendar);

    //id로 일정 하나 찾기
    Calendar findById(Long id);

    //모든 일정 찾기
    List<CalendarListDTO> findAll();
}
