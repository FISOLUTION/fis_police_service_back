package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Schedule;

//고준영
public interface ScheduleRepository {

    void save(Schedule schedule);
    Schedule findById(Long id);
}
