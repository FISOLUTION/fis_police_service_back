package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.OfficialSaveRequest;
import fis.police.fis_police_server.dto.WellDoneResponse;

import javax.servlet.http.HttpServletRequest;

/*
    작성 날짜: 2022/02/14 11:33 오전
    작성자: 고준영
    작성 내용: 시설 담당자 관련
*/
public interface OfficialsController {

    // 시설 담당자 추가 (회원 가입)
    WellDoneResponse saveOfficials(OfficialSaveRequest request);
    WellDoneResponse modifyOfficials(OfficialSaveRequest request, HttpServletRequest httpServletRequest);
}
