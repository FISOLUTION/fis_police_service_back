package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallHistoryResponse {

    private Long user_id;           //DB id
    private String u_nickname;      //로그인 사용자 id
    private String u_name;          //사용자 이름
    private UserAuthority u_auth;   //사용자 권한 (ADMIN, USER, FIRED)

    private int today_call_num;     //오늘 통화 건수
    private int participation_num;  //참여 건수
    private int reject_num;         //거부 건수
    private int hold_num;           //보류 건수
    private int none_num;           //기타 건수
}
