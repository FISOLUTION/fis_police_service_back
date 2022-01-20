package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.repository.AgentRepository;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/*
    작성날짜: 2022/01/18 4:39 PM
    작성자: 이승범
    작성내용:
*/
@SpringBootTest
class AgentServiceImplTest {

    @Autowired
    AgentServiceImpl agentService;
    @Autowired
    MapConfig mapConfig;

    @Test
    void saveAgent_현장요원_코드중복() throws Exception {
        // given
        AgentSaveRequest agentSaveRequest1 = new AgentSaveRequest("이승범", "010-6715-0071", "321",
                "구로구 벚꽃로 68길 10", false, "", null);
        AgentSaveRequest agentSaveRequest2 = new AgentSaveRequest("이승범", "010-6715-0071", "321",
                "구로구 벚꽃로 68길 10", false, "", null);
        agentService.saveAgent(agentSaveRequest1);
        // when

        // then
        assertThrows(IllegalStateException.class, () -> {
            agentService.saveAgent(agentSaveRequest2);
        });
    }

    @Test
    public void saveAgent_잘못된주소입력() throws Exception {
        // given
        AgentSaveRequest agentSaveRequest = new AgentSaveRequest("이승범", "123", "12312312",
                "ㅁㄴㅇ", true, "", null);
        // when

        // then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            agentService.saveAgent(agentSaveRequest);
        });
    }

    @Test
    void modifyAgent() {
    }

    @Test
    void getAgent() {
    }
}