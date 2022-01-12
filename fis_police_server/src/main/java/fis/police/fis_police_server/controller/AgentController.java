package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.dto.AgentModifyRequest;
import fis.police.fis_police_server.dto.AgentSaveRequest;

import java.util.List;

// 이승범
public interface AgentController {
    // 현장요원 추기
    void saveAgent(AgentSaveRequest request);

    // 현장요원 수정
    void modifyAgent(AgentModifyRequest request);

    // 현장요원 조회
    List<Agent> getAgent();
}
