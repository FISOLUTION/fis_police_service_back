package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.*;

import javax.mail.MessagingException;

//고준영
public interface CallController {
    // 연락기록 저장
    CallSaveResponse saveCall(CallSaveRequest request) throws MessagingException;
    // 해당 날짜별 콜 직원의 통화 기록 현황
    Result callNumByDate (String date);
}
