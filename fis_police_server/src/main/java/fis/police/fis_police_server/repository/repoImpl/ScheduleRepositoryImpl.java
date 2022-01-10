package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.repository.ScheuduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheuduleRepository {

    private final EntityManager em;

    @Override
    public void save(Schedule schedule) {
        em.persist(schedule);
    }

    @Override
    public Schedule findById(Long id) {
        Schedule findScheById = em.find(Schedule.class, id);
        return findScheById;
    }
}
