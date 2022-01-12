package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
    작성날짜: 2022/01/10 5:41 PM
    작성자: 이승범
    작성내용: AgentServiceImpl 구현중
*/
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final MapConfig mapConfig;

    @Override // 현장요원 추가
    @Transactional
    public void saveAgent(AgentSaveRequest request) throws ParseException, RestClientException,
            IllegalStateException, IndexOutOfBoundsException {
        validateDuplicateAgent(request);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000); // 연결시간 초과 5초
        factory.setReadTimeout(5000);   // 읽기시간 초과 5초
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-NCP-APIGW-API-KEY-ID", mapConfig.getApiId());
        httpHeaders.add("X-NCP-APIGW-API-KEY", mapConfig.getApiKey());
        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + request.getA_address();
        var responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody());
        JSONArray jsonArray = (JSONArray) jsonObject.get("addresses");
        JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
        HasCar hasCar = request.isA_hasCar() ? HasCar.CAR : HasCar.WALK;
        Agent agent = Agent.createAgent(request.getA_name(), request.getA_ph(),
                request.getA_code(), request.getA_address(), hasCar, request.getA_equipment(),
                request.getA_receiveDate(), jsonObject1.get("x").toString(), jsonObject1.get("y").toString());
        agentRepository.save(agent);
    }

    private void validateDuplicateAgent(AgentSaveRequest agentSaveRequest){
        List<Agent> findAgentList = agentRepository.findByA_code(agentSaveRequest.getA_code());
        if(!findAgentList.isEmpty()){
            throw new IllegalStateException("이미 존재하는 현장요원입니다.");
        }
    }

    @Override // 현장요원 수정
    public void modifyAgent() {
        Agent agent = agentRepository.findById()
    }

    @Override // 현장요원 조회
    public List<Agent> getAgent() {
        return null;
    }
}
