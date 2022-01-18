package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.LoginRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 원보라
public interface LoginController {


    String login(LoginRequest loginrequest, String redirectURL, HttpServletRequest request);

    String logout(HttpServletRequest request);

    String loginSuccess(Long loginUser, Model model);

}
