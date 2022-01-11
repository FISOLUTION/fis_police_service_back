package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    작성날짜: 2022/01/11 2:09 PM
    작성자: 이승범
    작성내용: 현장요원 추가를 위한 requestDTO
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentSaveRequest {

    private String a_name;                  //'현장 요원 이름'
    private String a_ph;                    //'현장 요원 전화번호',
    private String a_code;                  //'현장 요원 코드'
    private String a_address;               //'현장 요원 집 주소',
    private HasCar a_hasCar;                //'자차 여부'
    private String a_equipment;             //'장비 번호'
    private LocalDateTime a_receiveDate;    //'장비 수령 날짜'
//    private String a_latitude;              //'현장 요원 위도',
//    private String a_longitude;             //'현장 요원 경도',

}
