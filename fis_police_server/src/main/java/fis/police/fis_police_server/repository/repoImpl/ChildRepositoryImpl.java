package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Child;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ChildRepositoryImpl implements ChildRepository {

    private final EntityManager em;

    @Override
    public void save(Child child) {
        em.persist(child);
    }

    @Override
    public Child findById(Long id) {
        return em.find(Child.class, id);
    }

    @Override
    public void acceptChild(Long id, Accept accept) {
        em.createQuery("update Child c set c.accept =: accept where c.id =: id")
                .setParameter("id", id)
                .setParameter("accept", accept)
                .executeUpdate();
    }
}
