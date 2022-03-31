package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.repository.OfficialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/*
    작성 날짜: 2022/02/14 11:46 오전
    작성자: 고준영
    작성 내용: 시설 담당자 저장, 조회
*/
@Repository
@RequiredArgsConstructor
public class OfficialsRepositoryImpl implements OfficialsRepository {

    private final EntityManager em;


    @Override
    public void saveOfficials(Officials officials) {
        em.persist(officials);
    }

    @Override
    public Officials findById(Long id) {
        return em.find(Officials.class, id);
    }

    @Override
    public List<Officials> findAll() {
        return em.createQuery("select o from Officials o", Officials.class)
                .getResultList();
    }

    @Override
    public List<Officials> findByNickname(String nickname) {
        return em.createQuery("select o from Officials o where o.o_nickname = :nickname", Officials.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    @Override
    public void acceptOfficial(Long id, Accept accept) {
        em.createQuery("update Officials o set o.accept =: accept where o.id =: id")
                .setParameter("id", id)
                .setParameter("accept", accept)
                .executeUpdate();
    }

    @Override
    public List<Officials> findOfficialsWaitingAccept(Long center_id, Accept accept) {
        return em.createQuery("select o from Officials o where o.center.id =: center_id and o.accept =: accept", Officials.class)
                .setParameter("center_id", center_id)
                .setParameter("accept", accept)
                .getResultList();
    }

    @Override
    public void mappingClass(Long official_id, Aclass aclass) {
        em.createQuery("update Officials o set o.aclass =: aclass where o.id =: official_id")
                .setParameter("official_id", official_id)
                .setParameter("aclass", aclass)
                .executeUpdate();
    }
}
