package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.ScheduleGetResponse;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;

import java.time.LocalDate;
import java.util.List;

// 이승범
public interface ScheduleController {

        // 현장요원 배치
        Boolean assignAgent(ScheduleSaveRequest scheduleSaveRequest);

        // 날짜 별 현장요원 일정 리스트
        List<ScheduleGetResponse> selectDate(LocalDate date);

        // 일정 수정
        Boolean modifySchedule();

        // 일정 공지
        Boolean announceSchedule();
}
