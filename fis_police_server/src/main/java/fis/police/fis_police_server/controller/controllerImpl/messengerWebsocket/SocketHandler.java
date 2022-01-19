package fis.police.fis_police_server.controller.controllerImpl.messengerWebsocket;

import fis.police.fis_police_server.domain.Messenger;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.service.MessengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class SocketHandler extends TextWebSocketHandler {

    private final MessengerService messengerService;
    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("session = " + session);
        sessionMap.put(session.getId(), session);

//        // 전에 있던 메세지들 보내주기
//        User requestUser = (User) session.getAttributes().get("loginUser");
//        messengerService.getMessenger(requestUser).stream()
//                .forEach(msg -> {
//                    User user = msg.getUser();
//                    String textMsg = msg.getContext() + " " + msg.getSendTime() + " " + user.getU_name();
//                    try {
//                        session.sendMessage(new TextMessage(textMsg));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();

        Map<String, Object> attribute = session.getAttributes();
        User user = (User) attribute.get("loginUser");

        System.out.println("user.getU_name() = 핸들러 부분입니다" + user.getU_name());
        user.setId(2L);
        user.setU_auth(UserAuthority.ADMIN);
        Messenger messenger = new Messenger(msg, user);
        messengerService.saveMessenger(messenger);

        for(String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                // admin 과 보낸 사용자 에게만 보낸다.
                User acceptUser = (User) wss.getAttributes().get("loginUser");
//                if(acceptUser.getU_auth() == UserAuthority.ADMIN || wss.equals(session)) {
                    wss.sendMessage(new TextMessage("옛다!"));
//                }
            }catch(Exception e) {
                System.out.println("핸들링에서 발생 = " + e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        System.out.println("session 종료 = " + session);
        super.afterConnectionClosed(session, status);
    }
}
