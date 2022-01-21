package fis.police.fis_police_server.service;

import fis.police.fis_police_server.dto.ScheduleModifyRequest;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;
import fis.police.fis_police_server.dto.ScheduleByDateResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    // 현장요원 배치
    void assignAgent(ScheduleSaveRequest scheduleSaveRequest, Long userId) throws Exception;

    // 날짜 별 현장요원 일정 리스트
    List<ScheduleByDateResponse> selectDate(LocalDate localDate);

    // 일정 수정
    void modifySchedule(ScheduleModifyRequest request);

    /*
        작성날짜: 2022/01/19 4:31 PM
        작성자: 이승범
        작성내용: 일정취소 기능 추가
    */
    // 일정 취소
    void cancelSchedule(Long schedule_id);
}
