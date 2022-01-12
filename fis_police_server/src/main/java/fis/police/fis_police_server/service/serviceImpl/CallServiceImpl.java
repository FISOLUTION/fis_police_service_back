package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.dto.UserCallByDateResponse;
import fis.police.fis_police_server.repository.repoImpl.CallRepositoryImpl;
import fis.police.fis_police_server.repository.repoImpl.CenterRepositoryImpl;
import fis.police.fis_police_server.repository.repoImpl.UserRepositoryImpl;
import fis.police.fis_police_server.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/*
    작성 날짜: 2022/01/10 1:17 오후
    작성자: 고준영
    작성 내용:  call 기록 저장
*/
@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {

    private final CallRepositoryImpl callRepository;
    private final CenterRepositoryImpl centerRepository;
    private final UserRepositoryImpl userRepository;

    @Transactional
    @Override
    public CallSaveResponse saveCall(CallSaveRequest request, Center center, User user) {

        Call call = Call.createCall(request, center, user);
        callRepository.save(call);
        CallSaveResponse response = new CallSaveResponse();
        response.setCenter_id(call.getCenter().getId());
        response.setUser_id(call.getUser().getId());
        response.setCall_id(call.getId());
        response.setStatus_code("잘 저장됨");   // try catch 써야할 것으로 예상됨.

        return response;
    }

    /*
        작성 날짜: 2022/01/11 5:02 오후
        작성자: 고준영
        작성 내용: 한 날짜에 콜 직원 별 통화 건수를 알기 위한 함수. 아직 완성 못함 ㅅㅂ
    */
    @Override
    public UserCallByDateResponse userCallByDate(String date) {


        Call call3 = callRepository.findById(3L);
        System.out.println("call3.getUser().getId() = " + call3.getUser().getId());

        Call call5 = callRepository.findById(5L);
        System.out.println("call5.getDateTime() = " + call5.getDateTime());

        List<Call> calls = callRepository.callByDate("2021-12-11");
        for (Call call : calls) {
            System.out.println("call.getId() = " + call.getId());
        }

        List<Integer> test = callRepository.test();
        for (Integer integer : test) {
            System.out.println("integer = " + integer);
        }


//
//        List<Call> calls1 = callRepository.callByDate(date);
//        System.out.println("calls1.size() = " + calls1.size());
//        for (Call call : calls1) {
//            System.out.println("call = " + call);
//        }
//        List<Call> calls = callRepository.callByUser(date);
//        System.out.println("calls.size() = " + calls.size());
//        for (Call call : calls) {
//            System.out.println("call = " + call.getUser().getId());
//        }
//
//        int[] amount;
//        amount = new int[calls.size()];
//
//
//        for (int i : amount) {
//            amount[i] = calls.get(i).getNum();
//        }
//
//        for (Call call : calls) {
//            System.out.println("call = " + call);
//        }

        UserCallByDateResponse response = new UserCallByDateResponse();
//        response.setUserCall(amount);

        return response;
    }

    public Center findCenter(CallSaveRequest request) {
        Center findCenter = centerRepository.findById(request.getCenter_id());
        return findCenter;
    }

    public User findUser(CallSaveRequest request) {
        User findUser = userRepository.findById(request.getUser_id());
        return findUser;
    }

    /*
        작성 날짜: 2022/01/11 2:54 오후
        작성자: 고준영
        작성 내용: 정해진 날짜에 user별 통화 건수를 알아내기 위한 service
    */
    public void callByUser() {
        List<Call> calls = callRepository.callByUser("2021-12-11");
        int size = calls.size();
        int user[];
        user = new int[size];

    }

}
