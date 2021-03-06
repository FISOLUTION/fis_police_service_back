package fis.police.fis_police_server.service.interfaces;

import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.BoardDeleteRequest;
import fis.police.fis_police_server.dto.BoardListDTO;
import fis.police.fis_police_server.dto.BoardModifyRequest;
import fis.police.fis_police_server.dto.BoardSaveRequest;

import java.util.List;

/**
 * 2022/03/29/ 11:14 오전
 * 원보라
 * 알림장
 */
public interface BoardService {
    //알림장 게시물 추가
    Board saveBoard(Officials officials, BoardSaveRequest boardSaveRequest);

    //알림장 게시물 수정
    Board modifyBoard(BoardModifyRequest boardModifyRequest);

    //알림장 게시물 삭제
    Board deleteBoard(BoardDeleteRequest boardDeleteRequest);

    //알림장 하나 조회
    Board findById(Long id);

    //child 별 알림장 전체 조회 (확인 미확인 child 별로 보여줘야 하니까)
    List<BoardListDTO> getBoard(List<Long> checkBoardList);
}
