package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.Calendar;
import fis.police.fis_police_server.domain.QAclass;
import fis.police.fis_police_server.domain.QCalendar;
import fis.police.fis_police_server.domain.QOfficials;
import fis.police.fis_police_server.dto.CalendarListDTO;
import fis.police.fis_police_server.dto.QBoardListDTO;
import fis.police.fis_police_server.dto.QCalendarListDTO;
import fis.police.fis_police_server.repository.interfaces.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
/**
 *    날짜 : 2022/04/06 10:27 오전
 *    작성자 : 원보라
 *    작성내용 : 일정표
 */

@Repository
@RequiredArgsConstructor
public class CalendarRepositoryImpl implements CalendarRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;
    QCalendar qCalendar = QCalendar.calendar;
    QOfficials qOfficials = QOfficials.officials;
    QAclass qAclass = QAclass.aclass;

    @Override
    public void save(Calendar calendar) {
        em.persist(calendar);
    }

    @Override
    public Calendar findById(Long id) {
        return em.find(Calendar.class, id);
    }

    //삭제한 일정은 제외
    @Override
    public List<CalendarListDTO> findAll() {
        return jpaQueryFactory
                .select(new QCalendarListDTO(qCalendar.id, qCalendar.date, qCalendar.title, qCalendar.content, qCalendar.registration_date, qCalendar.modify_date, qCalendar.delete_date,qOfficials.id, qOfficials.o_name, qOfficials.o_nickname, qAclass.id, qAclass.name))
                .from(qCalendar)
                .leftJoin(qCalendar.officials, qOfficials)
                .leftJoin(qCalendar.aclass, qAclass)
                .where(qCalendar.delete_date.isNull())
                .orderBy(qCalendar.date.desc())  //일정의 날짜로 정렬해서 주기
                .fetch();
    }
}
