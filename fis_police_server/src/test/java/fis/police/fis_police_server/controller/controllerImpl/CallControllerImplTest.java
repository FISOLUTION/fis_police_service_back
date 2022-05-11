package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.dto.CallHistoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.List;

@WebAppConfiguration
@SpringBootTest
class CallControllerImplTest {

    @Autowired CallControllerImpl callController;

    @Test
    public void searchAllByDateTest() {
        List<CallHistoryResponse> calls = callController.searchAllByDate(LocalDate.now().toString());
        for (CallHistoryResponse call : calls) {
            System.out.println("call = " + call);
        }
    }

}