package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;

import java.util.List;

public interface AgentService {
    // 현장요원 추기
    void saveAgent(AgentSaveRequest agentSaveResponse) throws ParseException,
            RestClientException, IllegalStateException, IndexOutOfBoundsException;

    // 현장요원 수정
    void modifyAgent();

    // 현장요원 조회
    List<Agent> getAgent();
}
