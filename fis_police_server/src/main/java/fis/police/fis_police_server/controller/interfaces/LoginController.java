package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.dto.LoginResponse;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

// 원보라
public interface LoginController {


    LoginResponse login(LoginRequest loginrequest, String redirectURL, HttpServletRequest request);

    String logout(HttpServletRequest request);

    LoginResponse loginSuccess(Long loginUser, Model model, HttpServletRequest req);

}
