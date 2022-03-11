package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    날짜 : 2022/03/11 1:51 오후
    작성자 : 원보라
    작성내용 : 현장요원 현재 위치
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentLocationRequest {
    private String a_cur_lat;                          //현장 요원 현재 위도
    private String a_cur_long;                         //현장 요원 현재 경도
}
