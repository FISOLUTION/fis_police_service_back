package fis.police.fis_police_server.controller;

public interface UserController {
    // 콜직원 추기
    Boolean saveUser();

    // 콜직원 수정
    Boolean modifyUser();

    // 콜직원 조회
    List<User> getUser();
}
