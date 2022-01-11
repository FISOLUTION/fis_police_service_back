package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void saveAgent(Agent agent) {
        System.out.println(mapConfig.getApiId());
        System.out.println(mapConfig.getApiKey());
        agentRepository.save(agent);
    }

//    private void validateDuplicateAgent(Agent agent){
//        List<Agent> findAgentList = agentRepository.findById(agent.getId());
//        if(!findAgentList.isEmpty()){
//            throw new Exception("이미 존재하는 현장요원입니다.");
//        }
//    }

    @Override // 현장요원 수정
    public Boolean modifyAgent() {
        return null;
    }

    @Override // 현장요원 조회
    public List<Agent> getAgent() {
        return null;
    }
}
