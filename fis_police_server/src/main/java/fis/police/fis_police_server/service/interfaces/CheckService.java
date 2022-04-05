package fis.police.fis_police_server.service.interfaces;
import fis.police.fis_police_server.domain.Check;
import fis.police.fis_police_server.dto.CheckRequest;
import fis.police.fis_police_server.dto.ReadBoardList;

import java.util.List;

/**
 *    날짜 : 2022/03/31 4:28 오후
 *    작성자 : 원보라
 *    작성내용 : child 가 board 확인
 */
public interface CheckService {
    //확인 버튼
    Check saveCheck(CheckRequest checkRequest);

    //알림장 게시글 확인한 child list
    List<ReadBoardList> checkBoard(Long board_id);
}
