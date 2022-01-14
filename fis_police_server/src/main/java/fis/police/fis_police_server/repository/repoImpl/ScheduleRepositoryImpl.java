package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.dto.ScheduleByDateResponse;
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
//    @Override
//    public List<Schedule> findAllByDate(LocalDate date) {
//        return em.createQuery(
//                "select s from Schedule s" +
//                        " join fetch s.agent a" +
//                        " join fetch s.user" +
//                        " join fetch s.center" +
//                        " where s.visit_date = :date" +
//                        " order by a.a_name desc, s.visit_time desc", Schedule.class)
//                .setParameter("date", date)
//                .getResultList();
//    }

    @Override
    public List<ScheduleByDateResponse> findAllByDate(LocalDate date){
        return em.createQuery(
                "select new fis.police.fis_police_server.dto.ScheduleByDateResponse(" +
                        "s.id, a.a_code, a.a_name, c.id, c.c_name, c.c_address, c.c_ph, s.estimate_num, s.visit_date, " +
                        "s.visit_time, s.center_etc, s.agent_etc, s.modified_info, s.total_etc, s.call_check, s.call_check_info)" +
                        " from Schedule s " +
                        " join s.agent a" +
                        " join s.user u" +
                        " join s.center c" +
                        " where s.visit_date = :date" +
                        " order by a.a_name desc, s.visit_time desc", ScheduleByDateResponse.class)
                .setParameter("date", date)
                .getResultList();
    }
}
