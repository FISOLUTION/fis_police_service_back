package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.Call;
import lombok.*;

/*
    작성 날짜: 2022/01/12 5:38 오후
    작성자: 고준영
    작성 내용: 콜직원 아이디와 통화 건수를 저장하기 위한 dto
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CallNumDTO {

    private Long user_id;
    private int call_num;
}
