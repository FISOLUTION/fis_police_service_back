package fis.police.fis_police_server.dto;

import com.querydsl.core.annotations.QueryProjection;
import fis.police.fis_police_server.domain.enumType.AnnounceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    날짜 : 2022/04/06 2:29 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항 list
 */
@Data
@NoArgsConstructor
public class AnnounceListDTO {

    //공지사항 정보
    private Long announce_id;
    private String title;
    private String content;
    private String file;

    private String registration_date;
    private String registration_time;

    private String modify_date;
    private String modify_time;

    private String delete_date;
    private String delete_time;

    private Boolean external;           //외부공개 여부
    private AnnounceType announceType;  //글 종류

    //게시글 작성자 정보
    private Long official_id;
    private String o_name;
    private String o_nickname;

    //반 정보
    private Long aclass_id;
    private String name;

    @QueryProjection
    public AnnounceListDTO(Long announce_id, String title, String content, String file, String registration_date, String registration_time, String modify_date, String modify_time, String delete_date, String delete_time, Boolean external, AnnounceType announceType, Long official_id, String o_name, String o_nickname, Long aclass_id, String name) {
        this.announce_id = announce_id;
        this.title = title;
        this.content = content;
        this.file = file;
        this.registration_date = registration_date;
        this.registration_time = registration_time;
        this.modify_date = modify_date;
        this.modify_time = modify_time;
        this.delete_date = delete_date;
        this.delete_time = delete_time;
        this.external = external;
        this.announceType = announceType;
        this.official_id = official_id;
        this.o_name = o_name;
        this.o_nickname = o_nickname;
        this.aclass_id = aclass_id;
        this.name = name;
    }

    public AnnounceListDTO(Long announce_id, String title) {
        this.announce_id = announce_id;
        this.title = title;
    }
}
