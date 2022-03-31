package fis.police.fis_police_server.controller;

import javax.servlet.http.HttpServletRequest;

/*
    작성 날짜: 2022/03/30 5:41 오후
    작성자: 고준영
    작성 내용: 기본 정보, 설정 정보 (일부러 한 컨트롤러에 모아둠)
*/
public interface SettingController {
    Object basicOfficialInfo(HttpServletRequest request);
    Object basicAgentInfo(HttpServletRequest request);
    Object basicParentInfo(HttpServletRequest request);
}
