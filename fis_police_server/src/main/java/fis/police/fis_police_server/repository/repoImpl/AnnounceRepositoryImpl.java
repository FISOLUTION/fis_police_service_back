package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.Announce;
import fis.police.fis_police_server.domain.QAclass;
import fis.police.fis_police_server.domain.QAnnounce;
import fis.police.fis_police_server.domain.QOfficials;
import fis.police.fis_police_server.dto.AnnounceListDTO;
import fis.police.fis_police_server.dto.QAnnounceListDTO;
import fis.police.fis_police_server.dto.QBoardListDTO;
import fis.police.fis_police_server.repository.interfaces.AnnounceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *    날짜 : 2022/04/06 2:33 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항
 */
@Repository
@RequiredArgsConstructor
public class AnnounceRepositoryImpl implements AnnounceRepository {
    private final EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;
    QAnnounce qAnnounce = QAnnounce.announce;
    QOfficials qOfficials = QOfficials.officials;
    QAclass qAclass = QAclass.aclass;

    @Override
    public void save(Announce announce) {
        em.persist(announce);
    }

    @Override
    public Announce findById(Long id) {
        return em.find(Announce.class, id);
    }

    //삭제된 공지사항은 제외
    @Override
    public List<AnnounceListDTO> findAll() {
        return jpaQueryFactory
                .select(new QAnnounceListDTO(qAnnounce.id, qAnnounce.title, qAnnounce.content, qAnnounce.file, qAnnounce.registration_date, qAnnounce.registration_time, qAnnounce.modify_date, qAnnounce.modify_time, qAnnounce.delete_date, qAnnounce.delete_time, qAnnounce.external, qAnnounce.announceType,qOfficials.id, qOfficials.o_name, qOfficials.o_nickname, qAclass.id, qAclass.name))
                .from(qAnnounce)
                .leftJoin(qAnnounce.officials, qOfficials)
                .leftJoin(qAnnounce.aclass, qAclass)
                .where(qAnnounce.delete_date.isNull())
                .fetch();
    }
}
