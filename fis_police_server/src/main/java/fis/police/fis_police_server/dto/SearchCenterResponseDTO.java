package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Participation;
import fis.police.fis_police_server.domain.enumType.Visited;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchCenterResponseDTO {
    private Long c_id;
    private String c_address;
    private String c_ph;
    private Participation participation;
    private Visited visited;
}
