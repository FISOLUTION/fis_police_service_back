package fis.police.fis_police_server;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.Participation;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;


@Component
public class initDB {

    private final EntityManager em;
    private final InitService initService;

    @Autowired
    public initDB(EntityManager em, InitService initService) {
        this.em = em;
        this.initService = initService;
    }

//    @PostConstruct
//    public void in() {
//        initService.dbInit1();
//    }

    @Component
    @Transactional
    static class InitService {

        private final EntityManager em;

        public InitService(EntityManager em) {
            this.em = em;
        }

        /*
            날짜 : 2022/01/12 11:22 오전
            작성자 : 현승구
            작성내용 : init 할것 추가
        */


        public void dbInit1() {
            Center center = new Center("센터1", Participation.PARTICIPATION);
            System.out.println("em.getClass() = " + em.getClass());
            em.persist(center);
            em.flush();



            /*
                날짜 : 2022/01/13 1:16 오후
                작성자 : 원보라
                작성내용 : user init data 추가
            */
            User user1 =  new User( "fis001", "원보라", "1234", "010-0000-0001", LocalDate.of(2021,01,01), UserAuthority.ADMIN);
            em.persist(user1);

            User user2 =  new User( "fis002", "원보라2", "1234", "010-0000-0002", LocalDate.of(2022,02,02), UserAuthority.ADMIN);
            em.persist(user2);

            User user3 =  new User( "fis003", "원보라3", "1234", "010-0000-0003", LocalDate.of(2023,03,03), UserAuthority.USER);
            em.persist(user3);

            User user4 =  new User( "fis004", "원보라4", "1234", "010-0000-0004", LocalDate.of(2024,04,04), UserAuthority.USER);
            em.persist(user4);

            User user5 =  new User( "fis005", "원보라5", "1234", "010-0000-0005", LocalDate.of(2025,05,05), UserAuthority.FIRED);
            em.persist(user5);



        }
    }
}
