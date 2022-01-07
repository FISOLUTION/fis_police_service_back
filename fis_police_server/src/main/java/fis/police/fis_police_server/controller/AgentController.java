package fis.police.fis_police_server.controller;

public interface AgentController {
    // 현장요원 추기
    Boolean saveAgent();

    // 현장요원 수정
    Boolean modifyAgent();

    // 현장요원 조회
    List<Agent> getAgent();
}
