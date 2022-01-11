package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    작성 날짜: 2022/01/10 1:52 오후
    작성자: 고준영
    작성 내용: mail 전송 요청 api
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailSendRequest {

    private Long center_id;     // 그냥 웬지 필요할 것 같음...

    private String m_email;


}
