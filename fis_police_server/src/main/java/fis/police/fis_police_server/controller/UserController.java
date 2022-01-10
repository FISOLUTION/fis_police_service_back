package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.UserSaveRequest;
import fis.police.fis_police_server.dto.UserSaveResponse;

import java.util.List;

//원보라

public interface UserController  {
    // 콜직원 추가
    UserSaveResponse saveUser(UserSaveRequest request);

    // 콜직원 수정
    Boolean modifyUser();

    // 콜직원 조회
    List<User> getUser();
}
