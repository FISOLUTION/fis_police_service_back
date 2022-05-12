package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.AnnounceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *    날짜 : 2022/04/06 2:21 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnnounceSaveRequest {
    private Long aclass_id;

    private String title;               //글 제목
    private String content;             //글 내용
    private String file;                //첨부파일

    private String registration_date;   //등록 날짜
    private String registration_time;   //등록 시간

    private Boolean external;           //외부공개 여부
    private AnnounceType announceType;  //글 종류
}
