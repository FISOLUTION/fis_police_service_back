package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    날짜 : 2022/04/05 3:35 오후
 *    작성자 : 원보라
 *    작성내용 : 일정표 저장
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarSaveRequest {
    private Long aclass_id;

    private String date;                //해당하는 날짜
    private String title;               //글 제목
    private String content;             //글 내용

    private String registration_date;   //등록 날짜
}
