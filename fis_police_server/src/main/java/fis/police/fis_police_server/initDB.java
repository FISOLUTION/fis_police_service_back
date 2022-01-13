package fis.police.fis_police_server;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.enumType.Participation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


@Component
public class initDB {

    private final EntityManager em;
    private final InitService initService;

    @Autowired
    public initDB(EntityManager em, InitService initService) {
        this.em = em;
        this.initService = initService;
    }

    @PostConstruct
    public void in() {
        initService.dbInit1();
    }

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
        }

    }
}
