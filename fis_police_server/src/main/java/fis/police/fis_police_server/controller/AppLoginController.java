package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.dto.LoginResponse;

import javax.servlet.http.HttpServletRequest;

public interface AppLoginController {

    LoginResponse login(LoginRequest loginRequest);
    String logout(HttpServletRequest request);

}
