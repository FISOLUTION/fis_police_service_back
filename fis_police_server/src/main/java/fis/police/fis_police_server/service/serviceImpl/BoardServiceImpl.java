package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.BoardDeleteRequest;
import fis.police.fis_police_server.dto.BoardModifyRequest;
import fis.police.fis_police_server.dto.BoardSaveRequest;
import fis.police.fis_police_server.repository.AclassRepository;
import fis.police.fis_police_server.repository.BoardRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 2022/03/29/ 11:22 오전
 * 원보라
 * 알림장
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final OfficialsRepository officialsRepository;
    private final AclassRepository aclassRepository;


    @Override
    @Transactional
    public Board saveBoard(Officials officials, BoardSaveRequest boardSaveRequest) {
        Aclass findAclass = aclassRepository.findById(boardSaveRequest.getAclass_id());
        Board board = Board.createBoard(officials, findAclass, boardSaveRequest);
        boardRepository.save(board);
        return board;
    }

    @Override
    @Transactional
    //수정 날짜랑 시간 있으면 수정된겨
    public Board modifyBoard(BoardModifyRequest boardModifyRequest) {
        Board board = boardRepository.findById(boardModifyRequest.getBoard_id());
        Aclass findAclass = aclassRepository.findById(boardModifyRequest.getAclass_id());
        board.updateBoard(findAclass, boardModifyRequest);
        return board;
    }

    @Override
    @Transactional
    public Board deleteBoard(BoardDeleteRequest boardDeleteRequest) {
        Board board = boardRepository.findById(boardDeleteRequest.getBoard_id());
        board.deleteBoard(boardDeleteRequest); //디비에는 남아있고 삭제날짜와 시간만 업데이트
        return board;
    }

    @Override
    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public List<Board> getBoard() {
        return boardRepository.findAll();
    }
}
