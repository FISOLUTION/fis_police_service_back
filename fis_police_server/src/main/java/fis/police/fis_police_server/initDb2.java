package fis.police.fis_police_server;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.HasCar;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
    작성날짜: 2022/01/13 10:22 AM
    작성자: 이승범
    작성내용: ScheduleController 테스트를 위한 initDb
*/
@Component
@RequiredArgsConstructor
public class initDb2 {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit(){
            Agent agent1 = Agent.createAgent("asd", "123", "111", "분당구 불정로 6", HasCar.CAR,
                    "", LocalDateTime.now(), 123D, 123D);
            em.persist(agent1);
            Agent agent2 = Agent.createAgent("asd", "123", "222", "분당구 불정로 6", HasCar.CAR,
                    "", LocalDateTime.now(), 123D, 123D);
            em.persist(agent2);
            Agent agent3 = Agent.createAgent("asd", "123", "333", "분당구 불정로 6", HasCar.CAR,
                    "", LocalDateTime.now(), 123D, 123D);
            em.persist(agent3);
            Center center1 = new Center("111", "분당구 불정로 6", "123");
            em.persist(center1);
            Center center2 = new Center("111", "분당구 불정로 6", "123");
            em.persist(center2);
            Center center3 = new Center("111", "분당구 불정로 6", "123");
            em.persist(center3);
            User user1 = new User("111", "111", "111", "111",
                    LocalDate.now(), UserAuthority.USER);
            em.persist(user1);
            User user2 = new User("222", "222", "222", "222",
                    LocalDate.now(), UserAuthority.ADMIN);
            em.persist(user2);
            User user3 = new User("333", "333", "333", "333",
                    LocalDate.now(), UserAuthority.ADMIN);
            em.persist(user3);
            Schedule schedule1 = Schedule.createSchedule(center1, user1, agent1, LocalDateTime.now(),
                    LocalDate.now(), LocalTime.now(), 123, "111", "111", "111");
            em.persist(schedule1);
            Schedule schedule2 = Schedule.createSchedule(center2, user2, agent2, LocalDateTime.now(),
                    LocalDate.now(), LocalTime.now(), 123, "222", "222", "222");
            em.persist(schedule2);
        }
    }
}