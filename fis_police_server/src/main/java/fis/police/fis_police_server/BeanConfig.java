package fis.police.fis_police_server;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class BeanConfig {
    @Bean
    JPAQueryFactory JpaQueryFactory(EntityManager em){
        return new JPAQueryFactory(em);
    }
}
