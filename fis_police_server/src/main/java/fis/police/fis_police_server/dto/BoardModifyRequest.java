package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardModifyRequest {
    private Long board_id;

    private Long aclass_id;

    private String title;               //글 제목
    private String content;             //글 내용
    private String file;                //첨부파일

    private String modify_date;         //수정 날짜
    private String modify_time;         //수정 시간
}
