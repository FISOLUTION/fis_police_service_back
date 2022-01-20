package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CallController;
import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.CallRepository;
import fis.police.fis_police_server.service.serviceImpl.CallServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        // dateTime = 날짜와 시간이므로 date 부분과 time 부분을 자름 (날짜별 통화건수 등을 알아내기 위해 날짜와 시간을 분리함)
        String dateTime = request.getDateTime();
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);

        try{
            // 기관과 콜 직원을 찾는 과정에서 NullPointerException 발생 가능
            Center center = callService.findCenter(request);
            User user = callService.findUser(request);
            return callService.saveCall(request, center, user, date, time);
        } catch (NullPointerException e) {
            CallSaveResponse callSaveResponse = new CallSaveResponse();
            callSaveResponse.setCenter_id(request.getCenter_id());
            callSaveResponse.setUser_id(request.getUser_id());
            callSaveResponse.setStatus_code("center 혹은 user 존재하지 않음");
            return callSaveResponse;
        }
    }

}
