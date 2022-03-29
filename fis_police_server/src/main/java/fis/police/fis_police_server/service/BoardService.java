package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Board;

import java.util.List;

/**
 * 2022/03/29/ 11:14 오전
 * 원보라
 * 알림장
 */
public interface BoardService {
    //알림장 게시물 추가
    Board saveBoard(BoardSaveRequest boardSaveRequest);

    //알림장 게시물 수정
    Board modifyBoard(BoardSaveRequest boardSaveRequest);

    //알림장 하나 조회
    Board findById(Long id);

    //알림장 전체 조회
    List<Board> getBoard();

}
