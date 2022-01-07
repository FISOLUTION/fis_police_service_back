package fis.police.fis_police_server.controller;

import java.util.List;

public interface ScheduleController {

        // 현장요원 배치
        Boolean assignAgent();

        // 날짜 별 현장요원 일정 리스트
        List<Object> selectDate();

        // 일정 수정
        Boolean modifySchedule();

        // 일정 공지
        Boolean announceSchedule();
}
