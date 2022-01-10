package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.repository.CallRepository;
import fis.police.fis_police_server.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {

    private final CallRepository callRepository;

    @Transactional
    @Override
    public CallSaveResponse saveCall(CallSaveRequest request) {

        Call call = Call.createCall(request);
        callRepository.save(call);
        CallSaveResponse response = new CallSaveResponse();
        response.setId(call.getId());
        response.setStatus_code("잘 저장됨");   // try catch 써야할 것으로 예상됨.


        return response;
    }
}
