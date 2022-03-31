package fis.police.fis_police_server.repository.interfaces;

import fis.police.fis_police_server.domain.Board;

import java.util.List;

/**
 * 2022/03/29/ 11:06 오전
 * 원보라
 * 알림장
 */
public interface BoardRepository {
    void save(Board board);

    Board findById(Long id);

    List<Board> findAll();
}
