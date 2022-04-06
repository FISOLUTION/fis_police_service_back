package fis.police.fis_police_server.dto;

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
public class AnnounceDeleteRequest {
    private Long announce_id;

    private String delete_date;
    private String delete_time;
}
