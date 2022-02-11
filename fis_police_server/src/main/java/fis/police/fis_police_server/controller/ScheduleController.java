package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.dto.ScheduleModifyRequest;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

// 이승범영
// 원보라 - 앱
public interface ScheduleController {

        // 현장요원 배치
        void assignAgent(ScheduleSaveRequest scheduleSaveRequest, HttpServletRequest httpServletRequest, HttpServletResponse response);

        // 날짜 별 현장요원 일정 리스트
        Result selectDate(LocalDate date, HttpServletResponse response, HttpServletRequest httpServletRequest);

        // 일정 수정
        void modifySchedule(ScheduleModifyRequest scheduleModifyRequest, HttpServletResponse response, HttpServletRequest httpServletRequest);

        // 일정 취소
        void cancelSchedule(Long schedule_id, HttpServletResponse response, HttpServletRequest httpServletRequest);
}

