package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.dto.CallTodayDTO;
import fis.police.fis_police_server.dto.QCallTodayDTO;
import fis.police.fis_police_server.repository.CallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/*
    작성 날짜: 2022/01/10 10:17 오전
    작성자: 고준영
    작성 내용: call repository impl 기본 메서드 구현
*/
@Repository
@RequiredArgsConstructor
public class CallRepositoryImpl implements CallRepository {

    private final EntityManager em;

    /*
        날짜 : 2022/01/17 3:54 오후
        작성자 : 원보라
        작성내용 : querydsl
    */
    private final JPAQueryFactory jpaQueryFactory;
    QCall qCall = QCall.call;
    QUser qUser = QUser.user;


    @Override
    public void save(Call call) {
        em.persist(call);
    }

    @Override
    public Call findById(Long id) {

        Call findCallById = em.find(Call.class, id);
        return findCallById;
    }

    /*
        작성 날짜: 2022/01/12 3:09 오후
        작성자: 고준영
        작성 내용: 위의 findAll 함수에 date 변수를 삽입한 것, LocalDateTime으로 되어있는 값을 갖고 노는 방법 공부 후 이걸로 findAll 대체 예정
    */
    @Override
    public List<Call> testDate(String date) {
        return em.createQuery("select c from Call c where c.dateTime = : date", Call.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Call> callByCenter(Long id) {
        return em.createQuery("select c from Call c where c.center.id = : id", Call.class)
                .setParameter("id", id)
                .getResultList();
    }


    /*
        날짜 : 2022/01/17 3:50 오후
        작성자 : 원보라
        작성내용 : user 별 오늘 통화 건수
    */
    @Override
    //user 별 오늘 통화 건수
    public List<CallTodayDTO> todayCallNum(String today) {
        System.out.println("today ========= " + today);
        return jpaQueryFactory
                .select(new QCallTodayDTO(qCall.user.id, qCall.count()))
                .from(qCall)
                .where(qCall.dateTime.eq(today))
                .groupBy(qCall.user.id)
                .fetch();

        // 결과 예시 => CallTodayDTOList = [CallTodayDTO(user_id=13, call_num=2), CallTodayDTO(user_id=14, call_num=1)]
    }

    //user 별 총 통화 건수
    @Override
    public List<CallTodayDTO> totalCallNum() {
        return jpaQueryFactory
                .select(new QCallTodayDTO(qCall.user.id, qCall.count()))
                .from(qCall)
                .groupBy(qCall.user.id)
                .fetch();
    }

}
