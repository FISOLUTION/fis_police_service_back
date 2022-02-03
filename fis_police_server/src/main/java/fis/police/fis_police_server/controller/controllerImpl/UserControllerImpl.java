package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.UserController;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
    날짜 : 2022/01/10 2:45 오후
    작성자 : 원보라
    작성내용 : user controller
*/
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;


    // 콜직원 추가 및 수정
    @Override
    @PostMapping("/user")
    public void saveUser(@RequestBody UserSaveRequest request, HttpServletResponse response, HttpServletRequest req) {
        if (request.getUser_id() == null) { // 새로운 회원
            try {
                 userService.saveUser(request);//회원 가입
            } catch (IllegalStateException ie) { // 로그인 닉네임 중복 검사
                log.warn("로그인 id값 : [{}] {}",req.getSession().getAttribute("loginUser"),ie.getMessage());
                response.setStatus(402);
            }

        } else { //기존 회원일 경우 업데이트
            try {
                 userService.modifyUser(request);//회원 정보 수정
            } catch (IllegalStateException ie) { // 로그인 닉네임 중복 검사
                log.warn("로그인 id값 : [{}] {}",req.getSession().getAttribute("loginUser"),ie.getMessage());
                response.setStatus(402);
            }
        }
    }

    // 콜직원 조회 (처음 화면 접속시 보여주는 리스트)
    @Override
    @GetMapping("/user")
    public List<UserInfoResponse> getUser() {
        String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<UserInfoResponse> collect = userService.getUser();
        List<CallTodayDTO> callTodayList = userService.todayCallNum(now);
        List<CallAvgDTO> callAvgList = userService.totalCallNum();
        for (int i = 0; i < collect.size(); i++) {
            for (int j = 0; j < callTodayList.size(); j++) {
                if (collect.get(i).getUser_id().equals(callTodayList.get(j).getUser_id())) {
                    Long number = callTodayList.get(j).getCall_num();
                    collect.get(i).setToday_call_num(Math.toIntExact(number));
                }
            }
            for (int k = 0; k < callAvgList.size(); k++) {
                if (collect.get(i).getUser_id().equals(callAvgList.get(k).getUser_id())) {
                    Double number = callAvgList.get(k).getCall_avg_num();
                    number = Math.round(number * 10) / 10.0;
                    collect.get(i).setAverage_call(number);
                }
            }
        }
        return collect;
    }
}
