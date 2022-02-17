package fis.police.fis_police_server.service;

import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.dto.LoginResponse;

public interface AppLoginService {
    Long loginUserId(LoginRequest request);
    LoginResponse loginRes(LoginRequest request);
    LoginResponse loginCheck(Long loginUser);
}
