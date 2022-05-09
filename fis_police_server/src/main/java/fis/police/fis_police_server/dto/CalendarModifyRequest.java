package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    날짜 : 2022/04/06 10:16 오전
 *    작성자 : 원보라
 *    작성내용 : 일정표 수정
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarModifyRequest {
    private Long calendar_id;           //수정할 일정표 id

    private Long aclass_id;

    private String date;                //해당하는 날짜
    private String title;               //글 제목
    private String content;             //글 내용

    private String modify_date;         //수정 날짜
}
