package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.AgentModifyRequest;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.dto.AgentGetResult;

/*
    작성날짜: 2022/01/12 3:38 PM
    작성자: 이승범
    작성내용: json 배열을 객체로 감싸기 위해 getAgent 반환값 변경
*/
// 이승범
public interface AgentController {
    // 현장요원 추기
    void saveAgent(AgentSaveRequest request);

    // 현장요원 수정
    void modifyAgent(AgentModifyRequest request);

    // 현장요원 조회
    AgentGetResult getAgent();
}
