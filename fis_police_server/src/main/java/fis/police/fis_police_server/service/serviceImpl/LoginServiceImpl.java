package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.LoginService;
import fis.police.fis_police_server.service.exceptions.LoginServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    public static final String SESSION_COOKIE_NAME = "sessionID";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();


    //로그인
    @Override
    @Transactional
    public Long login(LoginRequest request) {
        List<User> user = userRepository.findByNickname(request.getU_nickname());
        if (user.get(0).getU_pwd().equals(request.getU_pwd())) {
            return user.get(0).getId();
        } else return null;
    }

    //세션 저장
    @Override
    public void createSession(Object value, HttpServletResponse response) {
        //세션 id를 생성하고, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키 생성 후 저장
        Cookie SessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(SessionCookie);
    }

    //세션조회
    @Override
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    //세션만료
    @Override
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    @Override
    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }

        return Arrays.stream(request.getCookies()) //배열을 스트림으로
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }



}





//    @Override
//    public Object login() throws LoginServiceException{
//        try{
//            throw new LoginServiceException("존재하지 않는 아이디입니다");
//        } catch (LoginServiceException exception){
//            return 404;
//        } catch (NullPointerException exception){
//            return 404;
//        }
//        finally {
//         // try랑 catch 공통문
//        }
//    }
//
//    @Override
//    public Object logout() {
//        return null;
//    }

