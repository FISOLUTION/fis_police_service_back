package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;

    @Override // 현장요원 추가
    public void saveAgent(Agent agent) {
        agentRepository.save(agent);
    }

    @Override // 현장요원 수정
    public Boolean modifyAgent() {
        return null;
    }

    @Override // 현장요원 조회
    public List<Agent> getAgent() {
        return null;
    }
}
