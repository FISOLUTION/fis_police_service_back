package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.dto.BoardListDTO;
import fis.police.fis_police_server.dto.QBoardListDTO;
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

    private final JPAQueryFactory jpaQueryFactory;
    QBoard qBoard = QBoard.board;
    QOfficials qOfficials = QOfficials.officials;
    QAclass qAclass = QAclass.aclass;
    QChild qChild = QChild.child;

    @Override
    public void save(Board board) {
        em.persist(board);
    }

    @Override
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public List<BoardListDTO> findAll() {
        return jpaQueryFactory
                .select(new QBoardListDTO(qBoard.id, qBoard.title, qBoard.content, qBoard.file, qBoard.registration_date, qBoard.registration_time, qBoard.modify_date, qBoard.modify_time, qBoard.delete_date, qBoard.delete_time, qOfficials.id, qOfficials.o_name, qOfficials.o_nickname, qAclass.id, qAclass.name, qChild.id))
                .from(qBoard)
                .leftJoin(qBoard.officials, qOfficials)
                .leftJoin(qBoard.aclass, qAclass)
                .leftJoin(qBoard.childList, qChild)
                .fetch();
//        return em.createQuery("select b from Board b", Board.class)
//                .getResultList();
    }
}
