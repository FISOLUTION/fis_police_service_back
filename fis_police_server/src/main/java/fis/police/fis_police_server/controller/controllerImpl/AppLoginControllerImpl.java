package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AppLoginController;
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
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = appLoginService.loginRes(loginRequest);
        Long loginUserId = appLoginService.loginUserId(loginRequest);

        //로그인 실패
        if (!loginResponse.getSc().equals("success")) {
            log.error("[로그인 id값: {}] [url: {}] [로그인 실패]",loginUserId,"/app/login");
            throw new NullPointerException("ID Fail");
        }

        // 토큰 생성
        String token = tokenService.makeToken(loginUserId, loginResponse);
        loginResponse.setToken(token);

        return loginResponse;
    }

    @Override
    public String logout(HttpServletRequest request) {
        return null;
    }
}
