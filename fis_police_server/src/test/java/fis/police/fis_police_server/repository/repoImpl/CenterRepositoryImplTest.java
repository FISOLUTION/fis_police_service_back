package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.enumType.Participation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CenterRepositoryImplTest {

    @Autowired
    EntityManager em;
    @Autowired
    CenterRepositoryImpl centerRepository;

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findBSearchCenterDTO() {
    }

    @Test
    void findByIdAndFetchAll(){
        Center center = new Center("이승범", "가리봉동", "000");

        Call call1 = new Call(center);
        Call call2 = new Call(center);
        Call call3 = new Call(center);

        Schedule schedule1 = new Schedule(center, "s1");
        Schedule schedule2 = new Schedule(center, "s2");
        Schedule schedule3 = new Schedule(center, "s3");

        center.getCallList().add(call1);
        center.getCallList().add(call2);
        center.getCallList().add(call3);

        center.getScheduleList().add(schedule1);
        center.getScheduleList().add(schedule2);
        center.getScheduleList().add(schedule3);

        em.persist(center);
        em.flush();
        em.clear();

        Center center1 = centerRepository.findByIdAndFetchAll(center.getId());

        System.out.println("center1.getId() + center1.getC_name() = " + center1.getId() + center1.getC_name());
        center.getScheduleList().stream()
                .forEach(e -> {
                    System.out.println("e.getParticipation() = " + e.getCenter_etc());
                });
        // 다 가져오는데?
    }
}