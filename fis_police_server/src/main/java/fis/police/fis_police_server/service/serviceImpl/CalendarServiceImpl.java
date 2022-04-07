package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Calendar;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.CalendarDeleteRequest;
import fis.police.fis_police_server.dto.CalendarListDTO;
import fis.police.fis_police_server.dto.CalendarModifyRequest;
import fis.police.fis_police_server.dto.CalendarSaveRequest;
import fis.police.fis_police_server.repository.interfaces.AclassRepository;
import fis.police.fis_police_server.repository.interfaces.CalendarRepository;
import fis.police.fis_police_server.service.interfaces.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *    날짜 : 2022/04/06 10:52 오전
 *    작성자 : 원보라
 *    작성내용 : 일정표
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final AclassRepository aclassRepository;


    @Override
    public Calendar saveCalendar(Officials officials, CalendarSaveRequest calendarSaveRequest) {
        Aclass findAclass = aclassRepository.findById(calendarSaveRequest.getAclass_id());
        Calendar calendar = Calendar.createCalendar(officials, findAclass, calendarSaveRequest);
        calendarRepository.save(calendar);
        return calendar;
    }

    @Override
    public Calendar modifyCalendar(CalendarModifyRequest calendarModifyRequest) {
        Calendar calendar = calendarRepository.findById(calendarModifyRequest.getCalendar_id());
        Aclass findAclass = aclassRepository.findById(calendarModifyRequest.getAclass_id());
        calendar.updateCalendar(findAclass, calendarModifyRequest);
        return calendar;
    }

    @Override
    public Calendar deleteCalendar(CalendarDeleteRequest calendarDeleteRequest) {
        Calendar calendar = calendarRepository.findById(calendarDeleteRequest.getCalendar_id());
        calendar.deleteCalendar(calendarDeleteRequest);
        return calendar;
    }

    @Override
    public Calendar findById(Long id) {
        return calendarRepository.findById(id);
    }

    @Override
    public List<CalendarListDTO> findAll() {
        return calendarRepository.findAll();
    }

    @Override
    public List<CalendarListDTO> getSelectedCalendar(String year, String month) {
        return calendarRepository.getSelectedCalendar(year,month);
    }

}
