package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    날짜 : 2022/04/06 10:19 오전
 *    작성자 : 원보라
 *    작성내용 : 일정표 삭제
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalendarDeleteRequest {
    private Long calendar_id;           //삭제할 id

    private String delete_date;         //삭제 날짜
}
