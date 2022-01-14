package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.service.exceptions.LoginServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
//    Object login() throws LoginServiceException;
//
//    Object logout();

    Long login(LoginRequest request);

    //세션 생성 (로그인)
    void createSession(Object value, HttpServletResponse response);

    //세션 조회 (화면이동)
    Object getSession(HttpServletRequest request);

    //세션 만료 (로그아웃)
    void expire(HttpServletRequest request);

    Cookie findCookie(HttpServletRequest request, String cookieName);
}
