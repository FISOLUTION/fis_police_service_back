package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.enumType.Participation;
import fis.police.fis_police_server.domain.enumType.Visited;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelCenterDTO {
    private Long id;     // 'primary_key',
    private String c_sido;        // '시도',
    private String c_sigungu;     // '시군구',
    private String c_name;        // '시설명',
    private String c_type;        // '유형',
    private String c_status;      // '운영현황',
    private String c_address;     // '주소',
    private String c_zipcode;     // '우편번호',
    private String c_ph;          // '전화번호',
    private String c_faxNum;      // '팩스번호',
    private String c_people;      // '현원',
    private String c_hpAddress;   // '홈페이지주소',
    private Double c_latitude;    // '위도',
    private Double c_longitude;   // '경도',
}
