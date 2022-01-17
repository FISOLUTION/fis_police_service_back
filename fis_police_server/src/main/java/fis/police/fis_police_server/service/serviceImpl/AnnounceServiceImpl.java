package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.service.AnnounceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AnnounceServiceImpl implements AnnounceService {

    @Override
    public Boolean announceSchedule() {
        return null;
    }

    @Override
    public String getAccessToken(String auth_code) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000); // 연결시간 초과 5초
        factory.setReadTimeout(5000); // 읽기시간 초과 5초
        RestTemplate restTemplate = new RestTemplate(factory);
        return null;
    }

}
