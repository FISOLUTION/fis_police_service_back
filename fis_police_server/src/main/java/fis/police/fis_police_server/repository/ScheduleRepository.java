package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Schedule;

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
    List<Schedule> findAllByDate(LocalDate date);
}
