package fis.police.fis_police_server.service;

import fis.police.fis_police_server.dto.MailDTO;

import javax.mail.MessagingException;

public interface MailService {
    Boolean sendMail(String mail_address) throws MessagingException;
}
