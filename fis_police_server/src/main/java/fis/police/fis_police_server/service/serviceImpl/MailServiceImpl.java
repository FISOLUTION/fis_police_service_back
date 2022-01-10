package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.dto.MailDTO;
import fis.police.fis_police_server.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
/*
    작성 날짜: 2022/01/10 1:18 오후
    작성자: 고준영
    작성 내용:  mail service ... 잘 모르겠당ㅋㄹㅎㅋ
*/
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSenderImpl mailSender;
    private final Call call;

    @Override
    public Boolean sendMail(String mail_address) throws MessagingException {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // MimeMessage 뭐 이런거 써야 첨부파일도 가는 것 같다.

        MailDTO mail = new MailDTO();

        mail.setAddress(call.getM_email());  // 콜 이력에서 이메일 빼와서 dto에 저장하는 코드인데, service에서 해야할지 controller에서 해야하는 지 잘 모르겠다 ㅜㅜ


        simpleMailMessage.setTo(mail.getAddress());  // 보내는 이메일 주소
        simpleMailMessage.setSubject(mail.getTitle());   // 보내는 이메일 제목
        simpleMailMessage.setText(mail.getContext());    // 보내는 이메일 내용

        return null;
    }
}
