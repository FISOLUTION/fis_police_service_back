package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Parent;
import fis.police.fis_police_server.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ParentRepositoryImpl implements ParentRepository {

    private final EntityManager em;

    @Override
    public void save(Parent parent) {
        em.persist(parent);
    }

    @Override
    public Parent findById(Long id) {
        return em.find(Parent.class, id);
    }

    @Override
    public List<Parent> findByNickname(String nickname) {
        return em.createQuery("select p from Parent p where p.p_nickname =: nickname", Parent.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }


}
