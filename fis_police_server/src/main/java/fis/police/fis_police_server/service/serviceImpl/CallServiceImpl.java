package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.repository.CallRepository;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.repository.repoImpl.CallRepositoryImpl;
import fis.police.fis_police_server.repository.repoImpl.CenterRepositoryImpl;
import fis.police.fis_police_server.repository.repoImpl.UserRepositoryImpl;
import fis.police.fis_police_server.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        response.setId(call.getId());
        response.setStatus_code("잘 저장됨");   // try catch 써야할 것으로 예상됨.

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
