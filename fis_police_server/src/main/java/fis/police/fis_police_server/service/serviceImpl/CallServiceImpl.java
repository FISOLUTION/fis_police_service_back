package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallNumDTO;
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
import java.util.ArrayList;
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

        CallSaveResponse response = new CallSaveResponse();

        try {
            Call call = Call.createCall(request, center, user);
            callRepository.save(call);
            response.setCenter_id(call.getCenter().getId());
            response.setUser_id(call.getUser().getId());
            response.setCall_id(call.getId());
            response.setStatus_code("잘 저장됨");

            return response;
        } catch (NullPointerException e) {
            if (response.getCenter_id() == null && response.getUser_id() != null) {
                response.setStatus_code("center 없음");
            } else if (response.getCenter_id() != null && response.getUser_id() == null) {
                response.setStatus_code("user 없음");
            } else {
                response.setStatus_code("center, user 모두 없음");
            }
            return response;
        }
    }

    /*
        작성 날짜: 2022/01/11 5:02 오후
        작성자: 고준영
        작성 내용: 한 날짜에 콜 직원 별 통화 건수를 알기 위한 함수.
    */
//    @Transactional
    @Override
    public UserCallByDateResponse userCallByDate(String date) {


        // 날짜값만 param으로 추가해서, 해당 날짜에 해당하는 콜 기록 다 긁어온 다음에, user_id 별로 count해서 배열로 배열이나 뭐 암튼 넘겨주면 될 것 같음
        List<Call> all = callRepository.findAll();
        int[] userCall;
        userCall = new int[all.size()];

//        for (Call call : all) {
//            for (int i = 0; i < userCall.length; i++) {
//                if(call.getUser().getId() == i+1) {
//                    userCall[i]++;
//                }
//            }
//        }

        List<Call> calls = callRepository.testDate(date);
        for (Call call : calls) {
            System.out.println("call.getId() = " + call.getId());
        }
        for (Call call : calls) {
            for (int i = 0; i < userCall.length; i++) {
                if (call.getUser().getId() == i+1) {
                    userCall[i]++;
                }
            }
        }

        UserCallByDateResponse response = new UserCallByDateResponse();
        response.setUserCall(userCall);

        System.out.println("date = " + date);

        return response;
    }

    public List<CallNumDTO> test(String date) {

        List<Call> calls = callRepository.testDate(date);
        int[] record;
        record = new int[calls.size()];

        for (Call call : calls) {
            for(int i = 0; i <record.length; i++) {
                record[i]++;
            }
        }
        List<CallNumDTO> list = new ArrayList<>();
        return list;


    }

    public Center findCenter(CallSaveRequest request) {
        Center findCenter = centerRepository.findById(request.getCenter_id());
        return findCenter;
    }

    public User findUser(CallSaveRequest request) {
        User findUser = userRepository.findById(request.getUser_id());
        return findUser;
    }
}
