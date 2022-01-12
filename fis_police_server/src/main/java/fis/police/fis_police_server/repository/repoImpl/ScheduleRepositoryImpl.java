package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/*
    작성 날짜: 2022/01/10 1:16 오후
    작성자: 고준영
    작성 내용:  schedule repository 저장, 조회(id)
*/
@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final EntityManager em;

    @Override
    public void save(Schedule schedule) {
        em.persist(schedule);
    }

    @Override
    public Schedule findById(Long id) {
        Schedule findScheduleById = em.find(Schedule.class, id);
        return findScheduleById;
    }
}
