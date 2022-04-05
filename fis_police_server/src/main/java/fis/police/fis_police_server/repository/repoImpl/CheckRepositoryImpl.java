package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.dto.QReadBoardList;
import fis.police.fis_police_server.dto.ReadBoardList;
import fis.police.fis_police_server.repository.interfaces.CheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * 날짜 : 2022/03/31 4:31 오후
 * 작성자 : 원보라
 * 작성내용 : board <- check -> child
 */
@Repository
@RequiredArgsConstructor
public class CheckRepositoryImpl implements CheckRepository {
    private final EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;
    QBoard qBoard = QBoard.board;
    QOfficials qOfficials = QOfficials.officials;
    QAclass qAclass = QAclass.aclass;
    QCheck qCheck = QCheck.check;
    QChild qChild = QChild.child;

    @Override
    public void save(Check check) {
        em.persist(check);
    }

    @Override
    public Check findById(Long id) {
        return em.find(Check.class, id);
    }

    @Override
    public Long findByBoard_Child(Long board_id, Long child_id) {
        return jpaQueryFactory
                .select(qCheck.id)
                .from(qCheck)
                .where(qCheck.board.id.eq(board_id)
                        .and(qCheck.child.id.eq(child_id)))
                .fetchOne();
    }

    @Override
    public List<ReadBoardList> checkBoard(Long board_id) {
        return jpaQueryFactory
                .select(new QReadBoardList(qCheck.child.id, qChild.name, qCheck.check_date, qCheck.check_time))
                .from(qCheck)
                .leftJoin(qCheck.child, qChild)
                .where(qCheck.board.id.eq(board_id))
                .fetch();
    }
}
