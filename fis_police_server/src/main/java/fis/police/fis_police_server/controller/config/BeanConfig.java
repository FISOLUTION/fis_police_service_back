package fis.police.fis_police_server.controller.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;

@Configuration
public class BeanConfig {
    @Bean
    JPAQueryFactory JpaQueryFactory(EntityManager em){
        return new JPAQueryFactory(em);
    }

    @Bean
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000);
        factory.setConnectionRequestTimeout(5000);
        return factory;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
