package fis.police.fis_police_server.controller.controllerImpl.messengerWebsocket;

import fis.police.fis_police_server.domain.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
    날짜 : 2022/01/17 4:08 오후
    작성자 : 현승구
    작성내용 : HandshakeInterceptor 코드 작성
*/

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        // 위의 파라미터 중, attributes 에 값을 저장하면 웹소켓 핸들러 클래스의 WebSocketSession에 전달된다
        try {
            System.out.println("Before Handshake");
            ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;

            HttpServletRequest req =  ssreq.getServletRequest();

        /*String userId = req.getParameter("userid");
        System.out.println("param, id:"+userId);
        attributes.put("userId", userId);*/
            User user = new User();
            user.setU_name("현승구");
            req.getSession().setAttribute("loginUser", user);
            // HttpSession 에 저장된 이용자의 아이디를 추출하는 경우
            User user1 = (User) req.getSession().getAttribute("loginUser");
            attributes.put("loginUser", user1);
            System.out.println("HttpSession에 저장된 id:"+ user1.getU_name());
            return super.beforeHandshake(request, response, wsHandler, attributes);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return false;
        }

    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        System.out.println("After Handshake");

        super.afterHandshake(request, response, wsHandler, ex);
    }

}
