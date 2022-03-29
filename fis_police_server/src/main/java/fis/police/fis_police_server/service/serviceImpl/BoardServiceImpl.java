package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.dto.BoardSaveRequest;
import fis.police.fis_police_server.repository.BoardRepository;
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


    @Override
    @Transactional
    public Board saveBoard(BoardSaveRequest boardSaveRequest) {
        return null;
    }

    @Override
    @Transactional
    //수정 날짜랑 시간 있으면 수정된겨
    public Board modifyBoard(BoardSaveRequest boardSaveRequest) {
        return null;
    }

    @Override
    public Board findById(Long id) {
        return null;
    }

    @Override
    public List<Board> getBoard() {
        return null;
    }
}
