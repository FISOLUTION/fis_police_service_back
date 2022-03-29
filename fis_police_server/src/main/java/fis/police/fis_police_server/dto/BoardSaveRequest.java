package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

/**
 * 2022/03/29/ 11:14 오전
 * 원보라
 * 알림장
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardSaveRequest {
    private Long aclass_id;

    private String title;               //글 제목
    private String content;             //글 내용
    private String file;                //첨부파일

    private String registration_date;   //등록 날짜
    private String registration_time;   //등록 시간
}
