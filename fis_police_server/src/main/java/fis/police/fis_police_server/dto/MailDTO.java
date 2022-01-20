package fis.police.fis_police_server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
    작성 날짜: 2022/01/10 1:15 오후
    작성자: 고준영
    작성 내용:  mail 형식
*/
@Getter
@Setter
@NoArgsConstructor
public class MailDTO {

    private String from_address;
    private String to_address;
    private String title;
    private String context;

}
