package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.AppLoginRequest;
import fis.police.fis_police_server.dto.LoginResponse;

import javax.servlet.http.HttpServletRequest;

/*
    작성 날짜: 2022/02/18 5:47 오후
    작성자: 고준영
    작성 내용: 앱 로그인 (jwt 토큰 사용)
*/
public interface AppLoginController {

    LoginResponse login(AppLoginRequest loginRequest);
    String logout(HttpServletRequest request);

}
