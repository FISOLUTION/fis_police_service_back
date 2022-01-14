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
    @GetMapping("/testcall")
    public Result callNumByDate(@RequestParam String date) {

        List<Call> calls = callRepository.testDate(date);

        for (Call call : calls) {
            System.out.println("call.getUser().getId() = " + call.getUser().getId());
        }
        // 2, 2, 2, 5, 5, 6

        List<Long> test = new ArrayList<>();
        test.add(calls.get(0).getUser().getId());

        for (int i = 0; i < calls.size(); i++) {    // 6개 (2, 2, 2, 5, 5, 6)
            for (int j =0; j < test.size(); j++) {  // 1개 (2)
                if (test.get(j) != calls.get(i).getUser().getId()) {
                    System.out.println("test.get(j) = " + test.get(j));
                    System.out.println("calls.get(i).getUser().getId() = " + calls.get(i).getUser().getId());
                    test.add(calls.get(i).getUser().getId());
                }
            }
        }
        for (Long aLong : test) {
            System.out.println("aLong = " + aLong);
        }





        int[] record;
        record = new int[calls.size()];

        for (Call call : calls) {
            for (int i = 0; i < record.length; i++) {
                if (call.getUser().getId() == i+1) {
                    record[i]++;
                }
            }
        }
        List<CallNumDTO> list = new ArrayList<>();

        for (int i = 0; i < record.length; i++) {
            CallNumDTO data = new CallNumDTO(calls.get(i).getUser().getId(), record[i]);
            list.add(data);
        }

        List<CallNumDTO> collect = list.stream()
                .map(l -> new CallNumDTO(l.getUser_id(), l.getCall_num()))
                .collect(Collectors.toList());


        return new Result(collect);
    }
}
