package fis.police.fis_police_server.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    날짜 : 2022/04/06 11:41 오전
 *    작성자 : 원보라
 *    작성내용 : 삭제되지 않은 일정들 list
 */
@NoArgsConstructor
@Data
public class CalendarListDTO {
    private Long calendar_id;

    private String date;
    private String title;
    private String content;

    private String registration_date;
    private String modify_date;
    private String delete_date;

    //일정 작성자
    private Long official_id;
    private String o_name;
    private String o_nickname;

    //반 정보
    private Long aclass_id;
    private String name;

    @QueryProjection
    public CalendarListDTO(Long calendar_id, String date, String title, String content, String registration_date, String modify_date, String delete_date, Long official_id, String o_name, String o_nickname, Long aclass_id, String name) {
        this.calendar_id = calendar_id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.registration_date = registration_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
        this.official_id = official_id;
        this.o_name = o_name;
        this.o_nickname = o_nickname;
        this.aclass_id = aclass_id;
        this.name = name;
    }
}
