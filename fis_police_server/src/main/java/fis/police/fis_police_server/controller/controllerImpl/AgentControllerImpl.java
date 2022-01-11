package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AgentController;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.service.AgentService;
import fis.police.fis_police_server.service.serviceImpl.MapConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    작성날짜: 2022/01/11 1:39 PM
    작성자: 이승범
    작성내용: AgentControllerImpl 생성
*/
@RestController
@RequiredArgsConstructor
public class AgentControllerImpl implements AgentController {

    private final AgentService agentService;
    private final MapConfig mapConfig;

    @Override
    @PostMapping("/agent")
    public void saveAgent(@RequestBody AgentSaveRequest request) {
        agentService.saveAgent(request);
    }

    @Override
    public Boolean modifyAgent() {
        return null;
    }

    @Override
    public List<Agent> getAgent() {
        return null;
    }
}
