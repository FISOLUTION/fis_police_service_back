package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AppLoginController;
import fis.police.fis_police_server.dto.AppLoginRequest;
import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.dto.LoginResponse;
import fis.police.fis_police_server.service.AppLoginService;
import fis.police.fis_police_server.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AppLoginControllerImpl implements AppLoginController {

    private final AppLoginService appLoginService;
    private final TokenService tokenService;

    @Override
    @PostMapping("/app/login")
    public LoginResponse login(@RequestBody AppLoginRequest loginRequest) {
        LoginResponse loginResponse = appLoginService.login(loginRequest);
        Long loginUserId = appLoginService.loginUserId(loginRequest);

        //로그인 실패
        if (loginResponse.getSc().equals("idFail")) {
            log.error("[로그인 id값: {}] [url: {}] [로그인 실패]",loginUserId,"/app/login");
            throw new NullPointerException("ID Fail");
        } else if (loginResponse.getSc().equals("pwdFail")) {
            log.error("[로그인 id값: {}] [url: {}] [로그인 실패]", loginUserId, "/app/login");
            throw new NullPointerException("Password Fail");
        }

        log.info("[로그인 id값: {}] [url: {}] [로그인 성공]", loginUserId, "/app/login");
        log.info("[로그인 역할: {}]", loginRequest.getRole());

        // 토큰 생성
        String token = tokenService.makeToken(loginUserId, loginResponse, "access");  // accessToken 생성 (유효시간 30분)
        String refreshToken = tokenService.makeToken(loginUserId, loginResponse, "refresh");   // refreshToken 생성 (유효시간 7일)
        loginResponse.setToken(token);
        loginResponse.setRefreshToken(refreshToken);

        return loginResponse;
    }

    @Override
    public String logout(HttpServletRequest request) {
        return null;
    }
}
