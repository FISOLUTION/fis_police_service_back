package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;

import java.util.List;

public interface ScheduleService {
    // 현장요원 배치
    Boolean assignAgent(ScheduleSaveRequest scheduleSaveRequest) throws Exception;

    // 날짜 별 현장요원 일정 리스트
    List<Object> selectDate();

    // 일정 수정
    Boolean modifySchedule();

}
