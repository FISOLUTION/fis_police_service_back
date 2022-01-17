package fis.police.fis_police_server.service.serviceImpl;

import com.mysema.commons.lang.Pair;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import fis.police.fis_police_server.dto.AgentModifyRequest;
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
import org.springframework.http.ResponseEntity;
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


    @Override
    @Transactional // 현장요원 추가
    public void saveAgent(AgentSaveRequest request) throws ParseException, RestClientException,
            IllegalStateException, IndexOutOfBoundsException {

        validateDuplicateAgent(request); // 현장요원 코드 중복 검사
        Pair<Double, Double> pair = addressToLocation(request.getA_address());
        HasCar hasCar = request.isA_hasCar() ? HasCar.CAR : HasCar.WALK;
        Agent agent = Agent.createAgent(request.getA_name(), request.getA_ph(),
                request.getA_code(), request.getA_address(), hasCar, request.getA_equipment(),
                request.getA_receiveDate(), pair.getFirst(), pair.getSecond());
        agentRepository.save(agent);
    }

    private void validateDuplicateAgent(AgentSaveRequest agentSaveRequest) {
        List<Agent> findAgentList = agentRepository.findByA_code(agentSaveRequest.getA_code());
        if (!findAgentList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 현장요원 코드입니다.");
        }
    }

    @Override
    @Transactional // 현장요원 수정
    // 수정사항 없어도 update 쿼리 나가는 이슈있음
    public void modifyAgent(AgentModifyRequest request) throws IllegalStateException, ParseException,
            IndexOutOfBoundsException, RestClientException {
        Agent findAgent = agentRepository.findById(request.getId());
        // 현장요원 코드가 달라졌으면 중복검사
        if (!findAgent.getA_code().equals(request.getA_code())) {
            validateDuplicateModifyAgent(request);
        }
        HasCar hasCar = request.isA_hasCar() ? HasCar.CAR : HasCar.WALK;
        AgentStatus agentStatus = request.isA_status() ? AgentStatus.WORK : AgentStatus.FIRED;
        // 현장요원 주소가 바뀐 경우
        if (!request.getA_address().equals(findAgent.getA_address())) {
            Pair<Double, Double> pair = addressToLocation(request.getA_address());
            findAgent.modifyAgent(request.getA_name(), request.getA_ph(), request.getA_code(),
                    request.getA_address(), hasCar, request.getA_equipment(), request.getA_receiveDate(),
                    pair.getFirst(), pair.getSecond(), agentStatus);
        } else { // 현장요원 주소는 안바뀐 경우
            findAgent.modifyAgent(request.getA_name(), request.getA_ph(), request.getA_code(),
                    request.getA_address(), hasCar, request.getA_equipment(), request.getA_receiveDate(),
                    findAgent.getA_latitude(), findAgent.getA_longitude(), agentStatus);
        }
    }

    private void validateDuplicateModifyAgent(AgentModifyRequest agentModifyRequest) {
        List<Agent> findAgentList = agentRepository.findByA_code(agentModifyRequest.getA_code());
        if (!findAgentList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 현장요원 코드입니다.");
        }
    }

    @Override // 전체 현장요원 조회
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

    // NaverMap api를 사용하여 도로명 주소로 위도경도 알아내는 로직
    public Pair<Double, Double> addressToLocation(String address) throws ParseException, IndexOutOfBoundsException,
            RestClientException{
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000); // 연결시간 초과 5초
        factory.setReadTimeout(5000);   // 읽기시간 초과 5초
        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-NCP-APIGW-API-KEY-ID", mapConfig.getApiId());
        httpHeaders.add("X-NCP-APIGW-API-KEY", mapConfig.getApiKey());
        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address;
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject fullResponse = (JSONObject) jsonParser.parse(responseEntity.getBody());
        System.out.println(responseEntity.getBody());
        JSONArray jsonAddress = (JSONArray) fullResponse.get("addresses");
        JSONObject addressResponse = (JSONObject) jsonAddress.get(0);
        Double x = Double.parseDouble(addressResponse.get("x").toString());
        Double y = Double.parseDouble(addressResponse.get("y").toString());
        return new Pair<Double, Double>(x, y);
    }
}
