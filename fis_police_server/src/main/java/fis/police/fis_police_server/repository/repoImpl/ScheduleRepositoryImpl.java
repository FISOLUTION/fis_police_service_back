package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

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

    /*
        작성날짜: 2022/01/13 4:18 PM
        작성자: 이승범
        작성내용: findAllByDate 작성
    */
    @Override
    public List<Schedule> findAllByDate(LocalDate date) {
        return em.createQuery(
                "select s from Schedule s" +
                        " join fetch s.agent a" +
                        " join fetch s.user" +
                        " join fetch s.center" +
                        " where s.visit_date = :date" +
                        " order by a.a_name desc, s.visit_time desc", Schedule.class)
                .setParameter("date", date)
                .getResultList();
    }
}
