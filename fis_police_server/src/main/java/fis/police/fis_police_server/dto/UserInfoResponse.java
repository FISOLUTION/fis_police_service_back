package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
    날짜 : 2022/01/13 10:41 오전
    작성자 : 원보라
    작성내용 : 콜직원 페이지에 띄울 dto
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long user_id;
    private String u_nickname;
    private String u_name;
    private String u_pwd;
    private String u_ph;
    private LocalDate u_sDate;
    private UserAuthority u_auth;
}
