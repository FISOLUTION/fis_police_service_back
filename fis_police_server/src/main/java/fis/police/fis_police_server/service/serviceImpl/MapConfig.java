package fis.police.fis_police_server.service.serviceImpl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/*
    작성날짜: 2022/01/11 11:41 AM
    작성자: 이승범
    작성내용: naver map api configuration
*/
@Configuration
@Getter
public class MapConfig {

    @Value("${naver.client.id}")
    private String apiId;

    @Value("${naver.client.secret}")
    private String apiKey;

}
