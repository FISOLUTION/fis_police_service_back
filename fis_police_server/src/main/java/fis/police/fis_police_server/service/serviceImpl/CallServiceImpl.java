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
    public UserCallByDateResponse userCallByDate(LocalDateTime date) {


        // 날짜값만 param으로 추가해서, 해당 날짜에 해당하는 콜 기록 다 긁어온 다음에, user_id 별로 count해서 배열로 배열이나 뭐 암튼 넘겨주면 될 것 같음
        List<Call> all = callRepository.findAll();
        int[] userCall;
        userCall = new int[all.size()];

        for (Call call : all) {
            for (int i = 0; i < userCall.length; i++) {
                if(call.getUser().getId() == i+1) {
                    userCall[i]++;
                }
            }
        }
        System.out.println("userCall[0] = " + userCall[0]);
        System.out.println("userCall[1] = " + userCall[1]);
        System.out.println("userCall[2] = " + userCall[2]);
        System.out.println("userCall[3] = " + userCall[3]);

        UserCallByDateResponse response = new UserCallByDateResponse();
        response.setUserCall(userCall);

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
}
