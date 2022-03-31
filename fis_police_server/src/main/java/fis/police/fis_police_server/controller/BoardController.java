package fis.police.fis_police_server.controller;


import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.dto.BoardDeleteRequest;
import fis.police.fis_police_server.dto.BoardModifyRequest;
import fis.police.fis_police_server.dto.BoardResponse;
import fis.police.fis_police_server.dto.BoardSaveRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 2022/03/29/ 02:38 오후
 * 원보라
 * 알림장
 */
public interface BoardController {
    //게시글 추가
    void saveBoard(BoardSaveRequest boardSaveRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;

    //게시글 수정
    void modifyBoard(BoardModifyRequest boardModifyRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;

    //게시글 삭제 컬럼 업데이트
    void deleteBoard(BoardDeleteRequest boardDeleteRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;

    //조회(해당하는 유치원의 반 알림장만 조회)
    List<Board> getBoard() throws IOException;

//    //읽음 확인 버튼(누가누가 읽었는지)
//    void checkBoard(Board board, HttpServletRequest httpServletRequest, HttpServletResponse response);

}
