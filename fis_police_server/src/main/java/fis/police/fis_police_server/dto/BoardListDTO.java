package fis.police.fis_police_server.dto;


import com.querydsl.core.annotations.QueryProjection;
import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Child;
import fis.police.fis_police_server.domain.Officials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *    날짜 : 2022/03/31 10:53 오전
 *    작성자 : 원보라
 *    작성내용 : board 알림장 게시글 list
 */
@NoArgsConstructor
@Data
public class BoardListDTO {
    //게시글 정보
    private Long board_id;

    private String title;
    private String content;
    private String file;

    private String registration_date;
    private String registration_time;

    private String modify_date;
    private String modify_time;

    private String delete_date;
    private String delete_time;

    //게시글 작성자 정보
    private Long official_id;
    private String o_name;
    private String o_nickname;

    //반 정보
    private Long aclass_id;
    private String name;


    //child 별 확인 여부
    private boolean check;

    @QueryProjection
    public BoardListDTO(Long board_id, String title, String content, String file, String registration_date, String registration_time, String modify_date, String modify_time, String delete_date, String delete_time, Long official_id, String o_name, String o_nickname, Long aclass_id, String name) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.file = file;
        this.registration_date = registration_date;
        this.registration_time = registration_time;
        this.modify_date = modify_date;
        this.modify_time = modify_time;
        this.delete_date = delete_date;
        this.delete_time = delete_time;
        this.official_id = official_id;
        this.o_name = o_name;
        this.o_nickname = o_nickname;
        this.aclass_id = aclass_id;
        this.name = name;
    }

    public BoardListDTO(Long board_id, String title) {
        this.board_id = board_id;
        this.title = title;
    }
}
