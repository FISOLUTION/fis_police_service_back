package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.dto.ScheduleModifyRequest;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;

import java.time.LocalDate;

// 이승범
public interface ScheduleController {

        // 현장요원 배치
        void assignAgent(ScheduleSaveRequest scheduleSaveRequest);

        // 날짜 별 현장요원 일정 리스트
        Result selectDate(LocalDate date);

        // 일정 수정
        void modifySchedule(ScheduleModifyRequest scheduleModifyRequest);

        // 일정 취소
        void cancelSchedule(Long schedule_id);
}

