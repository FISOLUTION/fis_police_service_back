package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Schedule;
import org.springframework.stereotype.Repository;

//고준영현
@Repository
public interface ScheuduleRepository {

    void save(Schedule schedule);
    Schedule findById(Long id);
}
