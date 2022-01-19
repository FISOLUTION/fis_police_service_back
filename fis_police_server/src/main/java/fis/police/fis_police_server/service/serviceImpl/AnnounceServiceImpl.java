package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.dto.AgentGetResponse;
import fis.police.fis_police_server.service.AnnounceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/*
    작성날짜: 2022/01/18 11:20 AM
    작성자: 이승범
    작성내용: 카카오 메세지를 통한 일정공지를 위한 서비스 구현
*/
@Service
@Getter
@RequiredArgsConstructor
public class AnnounceServiceImpl implements AnnounceService {

    private final MapConfig mapConfig;

    @Override
    public void announceSchedule(String accessToken) {
        List<String> friendList = getFriendList(accessToken);
        System.out.println(friendList);
    }

    @Override
    public String getAccessToken(String auth_code) throws ParseException {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000); // 연결시간 초과 5초
        factory.setReadTimeout(5000); // 읽기시간 초과 5초
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", mapConfig.getKakaoApiKey());
        body.add("redirect_uri", mapConfig.getKakaoRedirectUri());
        body.add("code", auth_code);
        body.add("scope", "friends");
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token", HttpMethod.POST, kakaoTokenRequest, String.class);
        System.out.println(response.getBody());
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response.getBody());
        String accessToken = object.get("access_token").toString();
        String refreshToken = object.get("refresh_token").toString();
        return accessToken;
    }

    public List<String> getFriendList(String accessToken) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000); // 연결시간 초과 5초
        factory.setReadTimeout(5000); // 읽기시간 초과 5초
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {};
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                "https://kapi.kakao.com/v1/api/talk/friends",
                HttpMethod.GET, entity, typeRef);
        System.out.println(response.getBody());
        return null;
    }
}
