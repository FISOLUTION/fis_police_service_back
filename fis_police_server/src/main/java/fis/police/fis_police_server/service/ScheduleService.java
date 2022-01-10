package fis.police.fis_police_server.service;

public interface ScheduleService {
    // 현장요원 배치
    Boolean assignAgent(Agent agent, Center center, User user);

    // 날짜 별 현장요원 일정 리스트
    List<Object> selectDate();

    // 일정 수정
    Boolean modifySchedule();

}