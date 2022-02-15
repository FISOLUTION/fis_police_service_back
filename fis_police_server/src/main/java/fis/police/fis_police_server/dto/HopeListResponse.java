package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.domain.enumType.Complete;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HopeListResponse {
    private Long hope_id;
    // 시설과 신청자 정보 (그냥 이름으로 줘야하나?)
    private Long center_id;
    private Long official_id;

    private Accept accept;
    private Complete complete;

    private String h_date;
    private String h_time;
    private String h_mail;
    private String h_ph;

}
