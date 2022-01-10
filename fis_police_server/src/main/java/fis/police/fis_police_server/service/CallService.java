package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;

public interface CallService {
    // 연락기록 저장
    CallSaveResponse saveCall(CallSaveRequest request, Center center, User user);
}
