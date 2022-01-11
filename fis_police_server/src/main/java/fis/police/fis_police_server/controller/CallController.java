package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.dto.UserCallByDateRequest;
import fis.police.fis_police_server.dto.UserCallByDateResponse;

import javax.mail.MessagingException;

//고준영
public interface CallController {
    // 연락기록 저장
    CallSaveResponse saveCall(CallSaveRequest request) throws MessagingException;
    String test();
    UserCallByDateResponse userCallByDate(UserCallByDateRequest request);
}
