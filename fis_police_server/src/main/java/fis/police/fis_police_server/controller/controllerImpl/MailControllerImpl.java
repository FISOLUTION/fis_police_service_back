package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.MailController;
import fis.police.fis_police_server.dto.MailSendRequest;
import fis.police.fis_police_server.dto.MailSendResponse;
import fis.police.fis_police_server.service.serviceImpl.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
/*
    작성 날짜: 2022/01/10 2:07 오후
    작성자: 고준영
    작성 내용:  프론트에서 직접 받아온 메일 주소를 서비스 계층으로 보냄
*/
@RestController
@RequiredArgsConstructor
public class MailControllerImpl implements MailController {

    private final MailServiceImpl mailService;

    @GetMapping("/mail/send")
    @Override
    public MailSendResponse sendMail(@RequestBody MailSendRequest request) throws MessagingException {
        return mailService.sendMail(request);
    }
}
