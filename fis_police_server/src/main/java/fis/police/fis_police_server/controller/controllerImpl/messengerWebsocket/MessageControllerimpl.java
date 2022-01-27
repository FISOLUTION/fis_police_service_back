package fis.police.fis_police_server.controller.controllerImpl.messengerWebsocket;

import fis.police.fis_police_server.service.MessengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageControllerimpl {

    private final MessengerService messengerService;

    @GetMapping("message/{message_id}")
    private void delete(@PathVariable Long message_id){
        try {
            log.info("message controller 부분 = {}", message_id);
            messengerService.deleteMessenger(message_id);
        } catch (Exception e){
            log.error("exception e = {}",e);
        }

    }
}
