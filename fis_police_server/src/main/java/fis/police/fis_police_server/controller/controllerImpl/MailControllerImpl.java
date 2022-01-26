package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.MailController;
import fis.police.fis_police_server.dto.MailSendResponse;
import fis.police.fis_police_server.repository.repoImpl.CallRepositoryImpl;
import fis.police.fis_police_server.service.serviceImpl.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/*
    작성 날짜: 2022/01/10 2:07 오후
    작성자: 고준영
    작성 내용:  프론트에서 직접 받아온 메일 주소를 서비스 계층으로 보냄
*/
@RestController
@RequiredArgsConstructor
public class MailControllerImpl implements MailController {

    private final MailServiceImpl mailService;
    private final CallRepositoryImpl callRepository;

    @GetMapping("/center/{center_id}/sendmail")
    @Override
    public MailSendResponse sendMail(@PathVariable Long center_id) throws MessagingException {
        return mailService.sendMail(center_id);
    }

    @GetMapping("/mail/valid/test/{mail}")
    public String testMailValidate(@PathVariable String mail) {
        System.out.println("mail = " + mail);
        String result = "true";
        try {
            InternetAddress addr = new InternetAddress(mail);
            addr.validate();
        } catch (AddressException e) {
            result = "false";
        }
        return result;
    }
}
