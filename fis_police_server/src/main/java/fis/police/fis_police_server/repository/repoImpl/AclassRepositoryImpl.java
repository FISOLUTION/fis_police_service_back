package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.repository.AclassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 2022/03/29/ 11:06 오전
 * 원보라
 * 유치원 -반
 */
@Repository
@RequiredArgsConstructor
public class AclassRepositoryImpl implements AclassRepository {

    private final EntityManager em;

    @Override
    public void save(Aclass aclass) {
        em.persist(aclass);
    }

    @Override
    public Aclass findById(Long id) {
        return em.find(Aclass.class, id);
    }

    @Override
    public List<Aclass> findAll() {
        return null;
    }
}
