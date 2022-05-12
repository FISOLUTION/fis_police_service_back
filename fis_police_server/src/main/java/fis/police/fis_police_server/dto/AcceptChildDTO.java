package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Accept;
import lombok.Data;

@Data
public class AcceptChildDTO {
    private Accept accept;
    private Long child_id;
}
