package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.TokenSet;

import javax.servlet.http.HttpServletRequest;

/*
    작성 날짜: 2022/03/30 5:41 오후
    작성자: 고준영
    작성 내용: refresh token 발급
*/
public interface RefreshTokenController {

    TokenSet createAccessToken(HttpServletRequest request);
}