package fis.police.fis_police_server;

import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.domain.enumType.*;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;
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
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            Agent agent1 = Agent.createAgent("asd", "123", "111", "분당구 불정로 6", HasCar.CAR,
                    "", LocalDate.now(), 123D, 123D);
            em.persist(agent1);
            Agent agent2 = Agent.createAgent("asd", "123", "222", "분당구 불정로 6", HasCar.CAR,
                    "", LocalDate.now(), 123D, 123D);
            em.persist(agent2);
            Agent agent3 = Agent.createAgent("asd", "123", "333", "분당구 불정로 6", HasCar.CAR,
                    "", LocalDate.now(), 123D, 123D);
            em.persist(agent3);
            Center center1 = new Center("111", "분당구 불정로 6", "123", 123D, 123D);
            em.persist(center1);
            Center center2 = new Center("123", "분당구 불정로 6", "123", 123D, 123D);
            em.persist(center2);
            Center center3 = new Center("234", "분당구 불정로 6", "123", 123D, 123D);
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
            Schedule schedule1 = Schedule.createSchedule(center1, user1, agent1, LocalDate.now(),
                    LocalDate.now(), LocalTime.now(), 500, "특이사항 없음", "특이사항 없음", Accept.accept,null);
            em.persist(schedule1);
            Schedule schedule2 = Schedule.createSchedule(center1, user2, agent2, LocalDate.now(),
                    LocalDate.now(), LocalTime.now(), 500, "특이사항 없음", "특이사항 없음",Accept.accept,"차가 막혀요");
            em.persist(schedule2);

            Schedule schedule3 = Schedule.createSchedule(center1, user1, agent1, LocalDate.now(),
                    LocalDate.parse("2023-09-09"), LocalTime.now(), 333, "특이사항 없음", "특이사항 없음", Accept.accept,null);
            em.persist(schedule3);
            Schedule schedule4 = Schedule.createSchedule(center2, user2, agent1, LocalDate.now(),
                    LocalDate.now(),LocalTime.parse("10:00:00"), 444, "특이사항 없음", "특이사항 없음",Accept.accept,"차가 막혀요");
            em.persist(schedule4);

            Schedule schedule5 = Schedule.createSchedule(center3, user1, agent1, LocalDate.now(),
                    LocalDate.parse("2022-09-09"),LocalTime.parse("11:00:00"), 333, "특이사항 없음", "특이사항 없음", null,null);
            em.persist(schedule5);
            Schedule schedule6 = Schedule.createSchedule(center3, user2, agent1, LocalDate.now(),
                    LocalDate.parse("2022-09-09"),LocalTime.parse("09:00:00"), 444, "특이사항 없음", "특이사항 없음",null,"차가 막혀요");
            em.persist(schedule6);

/*
    날짜 : 2022/01/17 4:39 오후
    작성자 : 원보라
    작성내용 : call init db 추가
*/
            Call call1 = new Call(center1, user1, "2022-01-18", "12:23:12", Participation.PARTICIPATION, InOut.IN, "담당자1", "010-1111-1111", "@naver", 20, "...", "...");
            em.persist(call1);

            Call call2 = new Call(center2, user1, "2022-01-18", "18:49:48", Participation.PARTICIPATION, InOut.IN, "담당자2", "010-2222-2222", "@naver", 20, "...", "...");
            em.persist(call2);

            Call call3 = new Call(center1, user2, "2022-01-18", "10:37:12", Participation.PARTICIPATION, InOut.IN, "담당자3", "010-3333-3333", "@naver", 20, "...", "...");
            em.persist(call3);


            Call call4 = new Call(center2, user2, "2022-01-18", "14:11:37", Participation.PARTICIPATION, InOut.IN, "담당자4", "010-4444-4444", "@naver", 20, "...", "...");
            em.persist(call4);

            Call call5 = new Call(center2, user2, "2022-01-18", "11:39:28",Participation.PARTICIPATION, InOut.IN, "담당자5", "010-5555-5555", "@naver", 20, "...", "...");
            em.persist(call5);

            Call call6 = new Call(center2, user2, "2022-01-19", "11:39:28", Participation.PARTICIPATION, InOut.IN, "담당자6", "010-6666-6666", "@naver", 20, "...", "...");
            em.persist(call6);

            Call call7 = new Call(center2, user2, "2022-01-19", "11:39:28", Participation.PARTICIPATION, InOut.IN, "담당자7", "010-7777-7777", "@naver", 20, "...", "...");
            em.persist(call7);

            Call call8 = new Call(center2, user3, "2022-01-19", "11:39:28", Participation.PARTICIPATION, InOut.IN, "담당자8", "010-8888-8888", "@naver", 20, "...", "...");
            em.persist(call8);

            Call call9 = new Call(center2, user2, "2022-01-20", "11:39:29", Participation.PARTICIPATION, InOut.IN, "담당자9", "010-9999-9999", "@naver", 20, "...", "...");
            em.persist(call9);

            Call call10 = new Call(center2, user2, "2022-01-20", "11:39:210", Participation.PARTICIPATION, InOut.IN, "담당자10", "010-1010-1010", "@naver", 20, "...", "...");
            em.persist(call10);

            Call call11 = new Call(center2, user2, "2022-01-20", "11:39:211", Participation.PARTICIPATION, InOut.IN, "담당자11", "010-1111-1111", "@naver", 20, "…", "…");
            em.persist(call11);
        }
    }
}