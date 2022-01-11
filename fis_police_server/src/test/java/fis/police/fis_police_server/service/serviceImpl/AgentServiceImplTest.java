package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.repository.AgentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AgentServiceImplTest {

    @Autowired
    AgentServiceImpl agentService;
    @Autowired
    MapConfig mapConfig;

    @Test
    void saveAgent() {
        Agent agent = new Agent();
        System.out.println("=====================");
        agentService.saveAgent(agent);
        System.out.println(mapConfig.getApiKey());
    }

    @Test
    void modifyAgent() {
    }

    @Test
    void getAgent() {
    }
}