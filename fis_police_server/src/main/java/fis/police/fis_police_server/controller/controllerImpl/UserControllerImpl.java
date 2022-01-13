package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.UserController;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.UserInfoResponse;
import fis.police.fis_police_server.dto.UserSaveRequest;
import fis.police.fis_police_server.dto.UserSaveResponse;
import fis.police.fis_police_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    날짜 : 2022/01/10 2:45 오후
    작성자 : 원보라
    작성내용 : user controller
*/
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;


    // 콜직원 추가
    @Override
    @PostMapping("/user")
    public UserSaveResponse saveUser(@RequestBody UserSaveRequest request){
        System.out.println("1. 들어가지나?"+request.getUser_id());
        if(request.getUser_id() == null){ // 새로운 회원
            return userService.saveUser(request); //회원 가입
        } else { //기존 회원일 경우 업데이트
            return userService.modifyUser(request); //회원 정보 수정
        }
    }

    @Override
    public Boolean modifyUser() {
        return null;
    }

    // 콜직원 조회 (처음 화면 접속시 보여주는 리스트)
    @Override
    @GetMapping("/user")
    public List<UserInfoResponse> getUser() {
        return userService.getUser();
    }
}
