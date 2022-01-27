package fis.police.fis_police_server.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.apache.http.HttpStatus.SC_MOVED_PERMANENTLY;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
<<<<<<< HEAD
<<<<<<< HEAD

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
=======
        if (HttpMethod.OPTIONS.matches(request.getMethod())) { //preflight cors 방지
>>>>>>> 3c04f434a68e5682fb2f2e6c7a2e9d219a8d568b
=======
        if (HttpMethod.OPTIONS.matches(request.getMethod())) { //preflight cors 방지
>>>>>>> 7469238d25d1470b44478f2902b11a8fda2ab28b
            return true;
        }

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);
        System.out.println("session.getAttribute(\"loginUser\") = " + session.getAttribute("loginUser") + '\n');
        if (session == null || session.getAttribute("loginUser") == null) {
            log.info("미인증 사용자 요청");
            response.sendError(SC_MOVED_PERMANENTLY,"미인증 사용자 요청"); //301 이면 로그인 페이지 넘어가게
            return false;
        }
        return true;
    }
}
