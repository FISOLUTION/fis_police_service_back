package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CallController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.dto.UserCallByDateRequest;
import fis.police.fis_police_server.dto.UserCallByDateResponse;
import fis.police.fis_police_server.service.serviceImpl.CallServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

/*
    작성 날짜: 2022/01/10 1:13 오후
    작성자: 고준영
    작성 내용: call controller 기본 구상
*/
@RestController
@RequiredArgsConstructor
public class CallControllerImpl implements CallController {

    private final CallServiceImpl callService;


    @Override
    @PostMapping("/call")
    public CallSaveResponse saveCall(@RequestBody CallSaveRequest request) {

        Center center = callService.findCenter(request);
        User user = callService.findUser(request);

        return callService.saveCall(request, center, user);
    }

    @Override
    @GetMapping("/call/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/call/date")       // 내가 혼자 지은 url 임 , 회의 때 하지 않은 내용이라서
    @Override
    public UserCallByDateResponse userCallByDate(UserCallByDateRequest request) {

//        String date = request.getDate();
        LocalDateTime date = request.getDate();
        return callService.userCallByDate(date);

    }
}
