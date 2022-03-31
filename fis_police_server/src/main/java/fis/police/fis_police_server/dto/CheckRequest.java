package fis.police.fis_police_server.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 *    날짜 : 2022/03/31 10:53 오전
 *    작성자 : 원보라
 *    작성내용 : board 알림장 게시글 list
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckRequest {
    private Long board_id;      //읽은 게시물 id
    private Long child_id;      //읽은 child id
    private String check_date;  //확인날짜
    private String check_time;  //확인시간
}
