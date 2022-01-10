package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;

import java.util.List;

public interface AgentService {
    // 현장요원 추기
    Boolean saveAgent();

    // 현장요원 수정
    Boolean modifyAgent();

    // 현장요원 조회
    List<Agent> getAgent();
}
