package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.domain.Check;
import fis.police.fis_police_server.domain.Child;
import fis.police.fis_police_server.dto.CheckRequest;
import fis.police.fis_police_server.repository.interfaces.BoardRepository;
import fis.police.fis_police_server.repository.interfaces.CheckRepository;
import fis.police.fis_police_server.repository.interfaces.ChildRepository;
import fis.police.fis_police_server.service.interfaces.CheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 날짜 : 2022/03/31 4:28 오후
 * 작성자 : 원보라
 * 작성내용 : child 가 board 확인
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final BoardRepository boardRepository;
    private final ChildRepository childRepository;

    @Override
    public Check saveCheck(CheckRequest checkRequest) {
        validateDuplicateCheck(checkRequest.getBoard_id(), checkRequest.getChild_id());
        Board board = boardRepository.findById(checkRequest.getBoard_id());
        Child child = childRepository.findById(checkRequest.getChild_id());
        Check check = Check.createCheck(board, child, checkRequest);
        checkRepository.save(check);
        return check;
    }

    private void validateDuplicateCheck(Long board_id, Long child_id) {
        Long check_id = checkRepository.findByBoard_Child(board_id, child_id);
        if (check_id != null) {
            //이미 확인한거임
            throw new IllegalCallerException("이미 확인한 알림장의 게시글 입니다.");
        }
    }
}
