package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDeleteRequest {
    private Long board_id;

    private String delete_date;         //삭제 날짜
    private String delete_time;         //삭제 시간
}
