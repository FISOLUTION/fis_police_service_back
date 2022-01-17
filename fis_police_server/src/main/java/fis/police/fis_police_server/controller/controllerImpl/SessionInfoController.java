package fis.police.fis_police_server.controller.controllerImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@CrossOrigin
@RestController
public class SessionInfoController {
    //세션 정보 출력
    @CrossOrigin
    @GetMapping("/login")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "세션이 없습니다.";
        }
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={},value={}", name, session.getAttribute(name)));

        log.info("sessionId={}",session.getId());
        log.info("getMaxInactiveInterval={}",session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));//세션 생성 시간
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));//마지막 세션 접근 시간
        log.info("isNew={}", session.isNew());

        return "세션 출력";
    }
}
