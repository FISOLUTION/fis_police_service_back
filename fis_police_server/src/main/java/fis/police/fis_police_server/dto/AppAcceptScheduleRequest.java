package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Accept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppAcceptScheduleRequest {
    private Long schedule_id;
    private Accept accept;
}
