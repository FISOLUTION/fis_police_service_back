package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

//고준영
public interface CallController {
    // 연락기록 저장
    CallSaveResponse saveCall(CallSaveRequest request, HttpServletRequest req) throws MessagingException;
}
