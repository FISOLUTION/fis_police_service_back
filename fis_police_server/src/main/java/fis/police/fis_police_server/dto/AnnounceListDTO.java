package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnounceListDTO {
    private Long announce_id;
    private String title;
}
