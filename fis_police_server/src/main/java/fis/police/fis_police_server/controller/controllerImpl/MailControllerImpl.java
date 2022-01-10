package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.MailController;
import fis.police.fis_police_server.service.MailService;
import fis.police.fis_police_server.service.serviceImpl.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    작성 날짜: 2022/01/10 1:13 오후
    작성자: 고준영
    작성 내용: mail controller 기본 구상
*/
@RestController
@RequiredArgsConstructor
public class MailControllerImpl implements MailController {

    private final MailServiceImpl mailService;


    @Override
    @GetMapping("/mail/send")
    public Boolean sendMail() {

        // 1. 프론트에서 그때그때 전송해야할 메일 주소를 백으로 보내준다.
        // 2. 저장된 콜기록에서 메일 주소를 꺼내어서 메일을 보낸다.
        // 3. 해당 시설의 콜기록 중 가장 최근 것의 콜 기록에서 메일 주소를 꺼내어서 메일을 보낸다. (말이 안됨.)

        return null;
    }



}
