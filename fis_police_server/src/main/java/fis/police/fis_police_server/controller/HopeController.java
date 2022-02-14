package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.HopeSaveRequest;

import javax.servlet.http.HttpServletRequest;

/*
    작성 날짜: 2022/02/14 11:33 오전
    작성자: 고준영
    작성 내용: 신청서 작성(제출), 신청서 조회
*/
public interface HopeController {
    // 신청서 작성
    void saveHope(HttpServletRequest request, HopeSaveRequest hopeRequest);

    // 신청서 리스트
    void getHope();
}
