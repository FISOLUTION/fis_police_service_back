package fis.police.fis_police_server.controller.controllerImpl;


import fis.police.fis_police_server.controller.BoardController;
import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.BoardService;
import fis.police.fis_police_server.service.OfficialService;
import fis.police.fis_police_server.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 2022/03/29/ 11:06 오전
 * 원보라
 * 알림장
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class BoardControllerImpl implements BoardController {
    private final BoardService boardService;
    private final TokenService tokenService;
    private final OfficialService officialService;


    /**
     * 게시글 추가
     *
     * @param boardSaveRequest
     * @param httpServletRequest
     * @param response
     */
    @Override
    @PostMapping("/board")
    public void saveBoard(@RequestBody BoardSaveRequest boardSaveRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        Officials findOfficial = officialService.findById(officialFromRequest.getId());
        log.info("[로그인 id값: {}] [url: {}] [요청: 알림장 게시글 추가]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/board");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            boardService.saveBoard(findOfficial, boardSaveRequest);            //성공시 200 ok
            log.info("알림장 게시물 추가 완료");
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoBoard");
        }
    }

    /**
     * 게시글 수정
     *
     * @param boardModifyRequest
     * @param httpServletRequest
     * @param response
     */
    @Override
    @PatchMapping("/board")
    public void modifyBoard(@RequestBody BoardModifyRequest boardModifyRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        log.info("[로그인 id값: {}] [url: {}] [요청: 알림장 게시글 수정]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/board");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            Board board = boardService.findById(boardModifyRequest.getBoard_id());
            if (officialFromRequest.getId().equals(board.getOfficials().getId())) { //게시글을 작성한 official 맞을 경우 수정 가능
                boardService.modifyBoard(boardModifyRequest);            //성공시 200 ok
                log.info("알림장 게시물 수정 완료");
            } else {
                throw new IllegalArgumentException("DifferentOfficial"); //다른 사람이 수정하려 할 때
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoBoard");
        }
//        catch (FileNotFoundException e) {
//            log.error(e.getMessage());
//            throw new FileNotFoundException("NoSuchFile");
//        }
    }

    /**
     * 게시글 삭제 컬럼 업데이트
     *
     * @param boardDeleteRequest
     * @param httpServletRequest
     * @param response
     */
    @Override
    @PatchMapping("/board/delete")
    public void deleteBoard(@RequestBody BoardDeleteRequest boardDeleteRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        log.info("[로그인 id값: {}] [url: {}] [요청: 알림장 게시글 삭제 업데이트]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/board/delete");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            Board board = boardService.findById(boardDeleteRequest.getBoard_id());
            if (officialFromRequest.getId().equals(board.getOfficials().getId())) {//게시글을 작성한 official 맞을 경우 삭제 가능
                boardService.deleteBoard(boardDeleteRequest);            //성공시 200 ok
                log.info("알림장 게시물 삭제 업데이트");
            } else {
                throw new IllegalArgumentException("DifferentOfficial"); //다른 사람이 수정하려 할 때
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoBoard");
        }
    }

    /**
     * 게시글 조회
     *
     * @return List<BoardListDTO>
     */
    @Override
    @GetMapping("/board")
    public List<BoardListDTO> getBoard() {
        return boardService.getBoard();
    }
}
