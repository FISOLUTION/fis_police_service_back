package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Board;
import fis.police.fis_police_server.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 2022/03/29/ 11:06 오전
 * 원보라
 * 알림장
 */
@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public void save(Board board) {
        em.persist(board);
    }

    @Override
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public List<Board> findAll() {
        return null;
    }
}
