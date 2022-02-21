package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
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



    private final JPAQueryFactory jpaQueryFactory;
    QSchedule qSchedule =QSchedule.schedule;
    QCenter qCenter = QCenter.center;
    QAgent qAgent = QAgent.agent;



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
    public List<ScheduleByDateResponse> findAllByDate(LocalDate date){
        return em.createQuery(
                "select new fis.police.fis_police_server.dto.ScheduleByDateResponse(" +
                        "s.id, a.a_code, a.a_name, c.id, c.c_name, c.c_address, c.c_ph, s.estimate_num, s.visit_date, " +
                        "s.visit_time, s.center_etc, s.agent_etc, s.modified_info, s.total_etc, s.call_check, s.call_check_info, s.accept)" +
                        " from Schedule s " +
                        " join s.agent a" +
                        " join s.user u" +
                        " join s.center c" +
                        " where s.visit_date = :date and s.valid = true" +
                        " order by a.a_name desc, s.visit_time", ScheduleByDateResponse.class)
                .setParameter("date", date)
                .getResultList();
    }



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
        날짜 : 2022/02/11 11:47 오전
        작성자 : 원보라
        작성내용 : 앱
    */
    //시설 - 방문 예정 일정들
    @Override
    public List<AppScheduleCenterResponse> findByCenter(Long center_id,LocalDate today) {
        return jpaQueryFactory
                .select(new QAppScheduleCenterResponse(qSchedule.id, qSchedule.visit_date,qSchedule.visit_time,qSchedule.estimate_num, qSchedule.center_etc,qSchedule.agent_etc,qSchedule.total_etc,qSchedule.accept,qSchedule.late_comment, qCenter.id, qCenter.c_latitude, qCenter.c_longitude, qAgent.id, qAgent.a_name, qAgent.a_ph, qAgent.a_code))
                .from(qSchedule)
                .leftJoin(qSchedule.agent, qAgent)
//                .fetchJoin() //querydsl 에서 dto 반환시 fetchjoin 대신 join
                .leftJoin(qSchedule.center, qCenter)
//                .fetchJoin()
                .distinct()
                .where(qSchedule.valid.eq(true) //스케쥴이 취소되지 않은 정상 스케쥴들 중에
                        .and(qSchedule.center.id.eq(center_id)) //해당 센터에 대한
                        .and(qSchedule.accept.eq(Accept.accept))    //현장요원이 수락한 즉 성립된 일정인
                        .and(qSchedule.visit_date.goe(today))   //오늘 날짜 일정과 그 이후의 날짜에 예약된 일정을 보여줌
                )
                .orderBy(qSchedule.visit_date.asc())//날짜 정렬해서 주기
                .fetch();
    }


//    public List<AppScheduleFilterDTO> findByCenterFilter(Long center_id,LocalDate today) {
//        return jpaQueryFactory
//                .select(new QAppScheduleFilterDTO(qSchedule.visit_date,qSchedule.visit_time, qCenter.id, qSchedule.count()))
//                .from(qSchedule)
//                .leftJoin(qSchedule.agent, qAgent)
//                .leftJoin(qSchedule.center, qCenter)
//                .distinct()
//                .where(qSchedule.valid.eq(true) //스케쥴이 취소되지 않은 정상 스케쥴들 중에
//                        .and(qSchedule.center.id.eq(center_id)) //해당 센터에 대한
//                        .and(qSchedule.accept.eq(Accept.accept))    //현장요원이 수락한 즉 성립된 일정인
//                        .and(qSchedule.visit_date.goe(today))   //오늘 날짜 일정과 그 이후의 날짜에 예약된 일정을 보여줌
//                )
//                .groupBy(qSchedule.visit_date, qSchedule.visit_time, qSchedule.center.id)
//                .orderBy(qSchedule.visit_date.asc())//날짜 정렬해서 주기
//                .fetch();
//    }
//
//    public List<Agent> findBySameSchedule(LocalDate visit_date, LocalTime visit_time, Long center_id){
//        return jpaQueryFactory
//                .select(qAgent)
//                .from(qSchedule)
//                .leftJoin(qSchedule.agent, qAgent)
//                .distinct()
//                .where(qSchedule.valid.eq(true) //스케쥴이 취소되지 않은 정상 스케쥴들 중에
//                        .and(qSchedule.visit_date.eq(visit_date))
//                        .and(qSchedule.visit_time.eq(visit_time))
//                        .and(qSchedule.center.id.eq(center_id)) //해당 하는 같은 스케줄의 agent 뽑기
//                )
//                .fetch();
//    }

    //현장요원 - 오늘 방문 일정
    @Override
    public List<AppScheduleAgentResponse> findByAgent(Long agent_id, LocalDate today) {
        return jpaQueryFactory
                .select(new QAppScheduleAgentResponse(qSchedule.id, qSchedule.visit_date,qSchedule.visit_time,qSchedule.estimate_num, qSchedule.center_etc,qSchedule.agent_etc,qSchedule.total_etc,qSchedule.accept,qSchedule.late_comment
                        ,qCenter.id ,qCenter.c_name, qCenter.c_address, qCenter.c_zipcode, qCenter.c_ph, qCenter.c_faxNum))
                .from(qSchedule)
                .leftJoin(qSchedule.center, qCenter)
                .distinct()
                .where(qSchedule.valid.eq(true) //스케쥴이 취소되지 않은 정상 스케쥴들 중에
                        .and(qSchedule.agent.id.eq(agent_id)) //해당 현장요원에 대한
                        .and(qSchedule.accept.eq(Accept.accept)) //현장요원이 수락한 즉 성립된 일정인
                        .and(qSchedule.visit_date.eq(today))    //오늘 날짜에 해당하는 일정을 보여줌
                )
                .orderBy(qSchedule.visit_time.asc())//시간으로 정렬해서 주기
                .fetch();
    }

    //현장요원 - 아직 수락/거절이 정해지지않은 스케쥴 리스트
    @Override
    public List<AppScheduleResponse> findByAgentIncompleteSchedule(Long agent_id) {
        return jpaQueryFactory
                .select(new QAppScheduleResponse(qSchedule.id, qSchedule.visit_date,qSchedule.visit_time,qSchedule.estimate_num, qSchedule.center_etc,qSchedule.agent_etc,qSchedule.total_etc
                        ,qCenter.id ,qCenter.c_name, qCenter.c_address, qCenter.c_zipcode, qCenter.c_ph, qCenter.c_faxNum))
                .from(qSchedule)
                .leftJoin(qSchedule.center, qCenter)
                .distinct()
                .where(qSchedule.valid.eq(true) //스케쥴이 취소되지 않은 정상 스케쥴들 중에
                        .and(qSchedule.agent.id.eq(agent_id)) //해당 현장요원에 대한
                        .and(qSchedule.accept.eq(Accept.TBD)) //현장요원이 수락|거부를 하지 않은 (accept 값이 TBD인 것들)
                )
                .orderBy(qSchedule.visit_date.asc(),qSchedule.visit_time.asc())// 방문 예쩡 날짜로 정렬해서 주기
                .fetch();
    }

    //현장요원 - 확정된 예정 스케줄 리스트
    @Override
    public List<AppScheduleResponse> findByAgentAllSchedule(Long agent_id,LocalDate today) {
        return jpaQueryFactory
                .select(new QAppScheduleResponse(qSchedule.id, qSchedule.visit_date,qSchedule.visit_time,qSchedule.estimate_num, qSchedule.center_etc,qSchedule.agent_etc,qSchedule.total_etc
                        ,qCenter.id ,qCenter.c_name, qCenter.c_address, qCenter.c_zipcode, qCenter.c_ph, qCenter.c_faxNum))
                .from(qSchedule)
                .leftJoin(qSchedule.center, qCenter)
                .distinct()
                .where(qSchedule.valid.eq(true) //스케쥴이 취소되지 않은 정상 스케쥴들 중에
                        .and(qSchedule.agent.id.eq(agent_id)) //해당 현장요원에 대한
                        .and(qSchedule.accept.eq(Accept.accept)) //현장요원이 수락한 즉 성립된 일정인
                        .and(qSchedule.visit_date.goe(today))    //오늘과 미래의 일정들
                )
                .orderBy(qSchedule.visit_date.asc(), qSchedule.visit_time.asc())//시간으로 정렬해서 주기
                .fetch();
    }
}
