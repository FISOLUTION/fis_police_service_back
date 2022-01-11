package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
    작성 날짜: 2022/01/11 3:49 오후
    작성자: 고준영
    작성 내용: 날짜별 콜직원의 통화건수를 알려주기 위함
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCallByDateRequest {

    private String date;
}
