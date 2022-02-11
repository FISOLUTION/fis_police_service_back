package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.dto.AppScheduleAgentResponse;
import fis.police.fis_police_server.dto.AppScheduleCenterResponse;
import fis.police.fis_police_server.dto.ScheduleByDateResponse;

import java.time.LocalDate;
import java.util.List;

//고준영
public interface ScheduleRepository {

    void save(Schedule schedule);
    Schedule findById(Long id);
    /*
        작성날짜: 2022/01/13 4:13 PM
        작성자: 이승범
        작성내용: findAllByDate 작성
    */
    List<ScheduleByDateResponse> findAllByDate(LocalDate date);

    /*
        날짜 : 2022/02/11 11:47 오전
        작성자 : 원보라
        작성내용 : 앱
    */
    //시설 - 방문 예정 일정들
    List<AppScheduleCenterResponse> findByCenter(Long center_id, LocalDate today);

    //현장요원 - 오늘 방문 일정
    List<AppScheduleAgentResponse> findByAgent(Long agent_id, LocalDate today);
}
