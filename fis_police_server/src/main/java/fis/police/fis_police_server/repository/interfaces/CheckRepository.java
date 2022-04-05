package fis.police.fis_police_server.repository.interfaces;

import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.domain.Check;
import fis.police.fis_police_server.domain.Child;

import java.util.List;

/**
 *    날짜 : 2022/03/31 4:31 오후
 *    작성자 : 원보라
 *    작성내용 : board <- check -> child
 */
public interface CheckRepository {
    void save(Check check);

    Check findById(Long id);

    Long findByBoard_Child(Long board_id, Long child_id);
}
