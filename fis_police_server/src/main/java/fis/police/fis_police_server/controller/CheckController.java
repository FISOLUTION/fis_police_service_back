package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.CheckRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *    날짜 : 2022/03/31 3:59 오후
 *    작성자 : 원보라
 *    작성내용 : child 알림장 확인 기능
 */
public interface CheckController {
    //알림장 확인 기능
    void SaveCheck(CheckRequest checkRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;
}
