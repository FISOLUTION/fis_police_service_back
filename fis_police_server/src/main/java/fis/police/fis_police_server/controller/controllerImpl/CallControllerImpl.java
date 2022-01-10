package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CallController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.service.serviceImpl.CallServiceImpl;
import fis.police.fis_police_server.service.serviceImpl.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
/*
    작성 날짜: 2022/01/10 1:13 오후
    작성자: 고준영
    작성 내용: call controller 기본 구상
*/
@RestController
@RequiredArgsConstructor
public class CallControllerImpl implements CallController {

    private final MailServiceImpl mailService;
    private final CallServiceImpl callService;


    @Override
    @PostMapping("/call")
    public CallSaveResponse saveCall(@RequestBody CallSaveRequest request) throws MessagingException {

        String m_email = request.getM_email(); // 콜 기록에서 센터의 이메일 주소를 받아온다.
        mailService.sendMail(m_email);


        Center center = callService.findCenter(request);
        User user = callService.findUser(request);

        return callService.saveCall(request, center, user);
    }
}
