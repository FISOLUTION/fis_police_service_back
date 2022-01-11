package fis.police.fis_police_server;

import fis.police.fis_police_server.domain.Center;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {

    private final EntityManager em;

    @PostConstruct
    public void initDB(){
        Center center = new Center();

    }
}
