package fis.police.fis_police_server.service.serviceImpl;

import com.mysema.commons.lang.Pair;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import fis.police.fis_police_server.dto.AgentModifyRequest;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.service.AgentService;
import fis.police.fis_police_server.service.MapService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import javax.validation.ConstraintViolationException;
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
    private final MapService mapService;

    @Override
    @Transactional // 현장요원 추가
    public Agent saveAgent(AgentSaveRequest request) throws ParseException, RestClientException,
            IllegalStateException, IndexOutOfBoundsException {

        validateDuplicateAgent(request.getA_code()); // 현장요원 코드 중복 검사

        Pair<Double, Double> pair = mapService.addressToLocation(request.getA_address());
        HasCar hasCar = request.isA_hasCar() ? HasCar.CAR : HasCar.WALK;

        Agent agent = Agent.createAgent(request, hasCar, pair.getFirst(), pair.getSecond());

        agentRepository.save(agent);
        return agent;

    }

    @Override
    @Transactional // 현장요원 수정
    // 수정사항 없어도 update 쿼리 나가는 이슈있음
    public Agent modifyAgent(AgentModifyRequest request) throws IllegalStateException, ParseException,
            IndexOutOfBoundsException, RestClientException {

        Agent findAgent = agentRepository.findById(request.getAgent_id());

        // 현장요원 코드가 달라졌으면 중복검사
        if (!findAgent.getA_code().equals(request.getA_code())) {
            validateDuplicateAgent(request.getA_code());
        }

        HasCar hasCar = request.isA_hasCar() ? HasCar.CAR : HasCar.WALK;
        AgentStatus agentStatus = request.isA_status() ? AgentStatus.WORK : AgentStatus.FIRED;

        // 현장요원 주소가 바뀐 경우
        if (!request.getA_address().equals(findAgent.getA_address())) {
            Pair<Double, Double> pair = mapService.addressToLocation(request.getA_address());
            findAgent.modifyAgent(request, hasCar, pair.getFirst(), pair.getSecond(), agentStatus);
        } else { // 현장요원 주소는 안바뀐 경우
            findAgent.modifyAgent(request, hasCar, findAgent.getA_longitude(), findAgent.getA_latitude(), agentStatus);
        }
        return findAgent;
    }

    @Override // 전체 현장요원 조회
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

    private void validateDuplicateAgent(String a_code) {
        List<Agent> findAgentList = agentRepository.findByA_code(a_code);
        if (!findAgentList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 현장요원 코드입니다.");
        }
    }

}
