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
    private final CallRepository callRepository;


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
    public UserCallByDateResponse userCallByDate(@RequestBody UserCallByDateRequest request) {

        String date = request.getDate();
//        LocalDateTime date = request.getDate();
        return callService.userCallByDate(date);

    }


    @GetMapping("/testcall")
    public Result callNumByDate(@RequestParam String date) {

        System.out.println("date = " + date);

        List<Call> calls = callRepository.testDate(date);

        System.out.println("calls.size() = " + calls.size());

        int[] record;
        record = new int[calls.size()];
        for (Call call : calls) {
            for (int i = 0; i < record.length; i++) {
                if (call.getUser().getId() == i+1) {
                    record[i]++;
                }
            }
        }
/*
    작성 날짜: 2022/01/12 5:36 오후
    작성자: 고준영
    작성 내용: 해당 날짜별 콜 직원의 통화 건수를 객체 배열로 전달하려고 했으나, 아래 list에서 record 값은 맞게 나오지만 id값이 조금 이상하게 나온다. 이걸 한번 고쳐보자
*/
        List<CallNumDTO> list = new ArrayList<>();
        CallNumDTO callnum = new CallNumDTO(calls.get(0).getUser().getId(), record[0]);
        list.add(callnum);

//        for (int i = 0; i < record.length; i++) {
//            CallNumDTO data = new CallNumDTO(calls.get(i).getUser().getId(), record[i]);
//            list.add(data);
//        }

        List<callNumDTO> collect = list.stream()
                .map(l -> new callNumDTO(l.getUser_id(), l.getCall_num()))
                .collect(Collectors.toList());


        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class callNumDTO {
        private Long user_id;
        private int call_num;
    }
}
