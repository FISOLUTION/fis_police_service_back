package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AgentController;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.service.AgentService;
import fis.police.fis_police_server.service.serviceImpl.MapConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
    작성날짜: 2022/01/11 1:39 PM
    작성자: 이승범
    작성내용: AgentControllerImpl 생성
*/
@RestController
@RequiredArgsConstructor
public class AgentControllerImpl implements AgentController {

    private final AgentService agentService;
    private final MapConfig mapConfig;

    @Override
    @PostMapping("/agent")
    public void saveAgent(@RequestBody AgentSaveRequest request) {
        try{
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectionRequestTimeout(5000);
            factory.setReadTimeout(5000);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("X-NCP-APIGW-API-KEY-ID", mapConfig.getApiId());
            httpHeaders.add("X-NCP-APIGW-API-KEY", mapConfig.getApiKey());
            String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="
                    + request.getA_address();
            final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            System.out.println(restTemplate.exchange(url, HttpMethod.GET, entity, String.class));
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Boolean modifyAgent() {
        return null;
    }

    @Override
    public List<Agent> getAgent() {
        return null;
    }
}
