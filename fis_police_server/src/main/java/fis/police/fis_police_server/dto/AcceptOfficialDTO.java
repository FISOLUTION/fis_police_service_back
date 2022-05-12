package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Accept;
import lombok.Data;

@Data
public class AcceptOfficialDTO {
    private Accept accept;  //accept or reject
    private Long official_id;   // 직원 id
}
