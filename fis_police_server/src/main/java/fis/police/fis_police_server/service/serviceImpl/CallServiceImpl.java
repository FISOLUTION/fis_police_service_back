package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.repoImpl.CallRepositoryImpl;
import fis.police.fis_police_server.repository.repoImpl.CenterRepositoryImpl;
import fis.police.fis_police_server.repository.repoImpl.UserRepositoryImpl;
import fis.police.fis_police_server.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                throw e;
            } else if (response.getCenter_id() != null && response.getUser_id() == null) {
                response.setStatus_code("user 없음");
                throw e;
            } else {
                response.setStatus_code("center, user 모두 없음");
                throw e;
            }
//            return response;
        }
    }

    @Override
    public Result callNumByDate(String date) {

        // 해당 날짜의 모든 콜 기록
        List<Call> calls = callRepository.testDate(date);

        // 콜 기록의 모든 user (중복 포함)
        List<Long> test = new ArrayList<>();
        for (Call call : calls) {
            test.add(call.getUser().getId());
        }
        // test 중복 제거
        Set<Long> set = new HashSet<Long>(test);
        // 중복이 제거된 userList (오름차순)
        List<Long> userList = new ArrayList<>(set);
        // user에 따른 콜 횟수를 담을 배열
        int[] callNum;
        callNum = new int[userList.size()];

        for (int i = 0; i < calls.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (calls.get(i).getUser().getId() == userList.get(j)) {
                    callNum[j]++;
                }
            }
        }

        List<CallNumDTO> list = new ArrayList<>();

        for (int i = 0; i < callNum.length; i++) {
            CallNumDTO data = new CallNumDTO(userList.get(i), callNum[i]);
            list.add(data);
        }

        List<CallNumDTO> collect = list.stream()
                .map(l -> new CallNumDTO(l.getUser_id(), l.getCall_num()))
                .collect(Collectors.toList());

        return new Result(collect);

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
