package fis.police.fis_police_server.controller.controllerImpl.messengerWebsocket;

import fis.police.fis_police_server.domain.Messenger;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.MessengerService;
import lombok.RequiredArgsConstructor;
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
public class SocketHandler extends TextWebSocketHandler {

    private final MessengerService messengerService;
    private final UserRepository userRepository;
    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("============================ 소켓 커낵션 생성이후 ==================================\n");
        System.out.println("session = " + session + '\n');
        sessionMap.put(session.getId(), session);

        // 전에 있던 메세지들 보내주기
        Long user_id = (Long) session.getAttributes().get("loginUser");
        User requestUser = userRepository.findById(user_id);

        messengerService.getMessenger(requestUser).stream()
                .forEach(msg -> {
                    User user = msg.getUser();
                    String textMsg = msg.getContext() + " " + msg.getSendTime() + " " + user.getU_name();
                    try {
                        session.sendMessage(new TextMessage(textMsg));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println("============================ 소켓 커낵션 이후 끝 ==================================\n");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();

        Map<String, Object> attribute = session.getAttributes();

        Long user_id = (Long) session.getAttributes().get("loginUser");
        User user = userRepository.findById(user_id);

        System.out.println("user.getU_name() = 핸들러 부분입니다" + user.getU_name()+ "\n");
        Messenger messenger = new Messenger(msg, user);
        messengerService.saveMessenger(messenger);

        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                // admin 과 보낸 사용자 에게만 보낸다.
                user_id = (Long) session.getAttributes().get("loginUser");
                User acceptUser = userRepository.findById(user_id);
                if(acceptUser.getU_auth() == UserAuthority.ADMIN || wss.equals(session)) {
                    wss.sendMessage(new TextMessage(messenger.getContext() + " " + messenger.getSendTime() + " " + user.getId() ));
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
