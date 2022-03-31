package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardResponse {
    private Long board_id;
    private String title;
    private String content;
    private String file;
    private String registration_date;
    private String registration_time;
    private String modify_date;
    private String modify_time;
}
