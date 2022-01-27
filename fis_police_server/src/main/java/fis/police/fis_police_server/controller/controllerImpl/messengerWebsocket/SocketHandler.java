package fis.police.fis_police_server.controller.controllerImpl.messengerWebsocket;

import fis.police.fis_police_server.domain.Messenger;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.MessengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class SocketHandler extends TextWebSocketHandler {

    private final MessengerService messengerService;
    private final UserRepository userRepository;
    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("소캣 연결 이후 초기 작업 진행 session = {}", session);
        sessionMap.put(session.getId(), session);

        // 전에 있던 메세지들 보내주기
        Long user_id = (Long) session.getAttributes().get("loginUser");
        User requestUser = userRepository.findById(user_id);

        messengerService.getMessenger(requestUser).stream()
                .forEach(msg -> {
                    User user = msg.getUser();
                    if(requestUser.getU_auth() == UserAuthority.ADMIN || requestUser.getId().equals(user.getId())) {
                        String textMsg = msg.getContext() + " " + msg.getSendTime() + " " + user.getU_name() + " " + msg.getId();
                        try {
                            session.sendMessage(new TextMessage(textMsg));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
        log.info("소캣 연결 이후 초기 작업 진행 session = {}", session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        log.info("session = {} 에서 message = {} 보냄", session, msg);
        Map<String, Object> attribute = session.getAttributes();
        Long user_id = (Long) session.getAttributes().get("loginUser");
        User user = userRepository.findById(user_id);

        Messenger messenger = new Messenger(msg, user);
        messengerService.saveMessenger(messenger);

        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                // admin 과 보낸 사용자 에게만 보낸다.
                user_id = (Long) wss.getAttributes().get("loginUser");
                User receiveUser = userRepository.findById(user_id);
                if(receiveUser.getU_auth() == UserAuthority.ADMIN || wss.equals(session)) {
                    wss.sendMessage(new TextMessage(messenger.getContext() + " " + messenger.getSendTime() + " " + user.getU_name() + " " + messenger.getId()));
                }
            }catch(Exception e) {
                System.out.println("핸들링에서 발생 = " + e + "\n");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        System.out.println("session 종료 = " + session + "\n");
        super.afterConnectionClosed(session, status);
    }
}
