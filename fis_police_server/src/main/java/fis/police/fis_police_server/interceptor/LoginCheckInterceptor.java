package fis.police.fis_police_server.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);
        System.out.println("session.getAttribute(\"loginUser\") = " + session.getAttribute("loginUser") + '\n');
        if (session == null || session.getAttribute("loginUser") == null) {
            log.info("미인증 사용자 요청");
            //로그인으로 redirect
//            response.sendRedirect("/login?redirectURL=" + requestURI); //나중에 url 정해지면 다시 보기
            response.sendRedirect("/login");
            response.sendError(499,"미인증 사용자 요청"); //흠  이게 가려낭 ?
            return false;
        }
        return true;
    }
}
