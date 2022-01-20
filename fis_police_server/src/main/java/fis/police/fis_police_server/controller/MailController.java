package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.MailSendRequest;
import fis.police.fis_police_server.dto.MailSendResponse;

import javax.mail.MessagingException;

// 고준영
public interface MailController {

    // 시설에 메일 전송
    MailSendResponse sendMail(Long center_id) throws MessagingException;
}
