package fis.police.fis_police_server.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadBoardList {
    private Long child_id;
    private String name;
    private String check_date;
    private String check_time;

    @QueryProjection
    public ReadBoardList(Long child_id, String name, String check_date, String check_time) {
        this.child_id = child_id;
        this.name = name;
        this.check_date = check_date;
        this.check_time = check_time;
    }
}
