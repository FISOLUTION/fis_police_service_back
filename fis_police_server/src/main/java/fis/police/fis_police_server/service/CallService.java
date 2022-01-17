package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.dto.UserCallByDateResponse;

import java.time.LocalDateTime;

public interface CallService {
    // 연락기록 저장
    CallSaveResponse saveCall(CallSaveRequest request, Center center, User user);
    // 해당 날짜별 콜 직원의 통화 기록 현황
    Result callNumByDate (String date);
}
