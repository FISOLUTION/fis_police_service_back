package fis.police.fis_police_server.service;

import fis.police.fis_police_server.dto.MailDTO;
import fis.police.fis_police_server.dto.MailSendRequest;
import fis.police.fis_police_server.dto.MailSendResponse;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface MailService {
    MailSendResponse sendMail(Long center_id, HttpServletRequest request) throws MessagingException;
}
