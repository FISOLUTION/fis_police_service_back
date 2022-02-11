package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Confirm;
import fis.police.fis_police_server.repository.ConfirmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConfirmRepositoryImpl implements ConfirmRepository {

    private final EntityManager em;

    @Override
    public void save(Confirm confirm) {
        em.persist(confirm);
    }

    @Override
    public List<Confirm> findAll() {
        return em.createQuery("select c from Confirm c", Confirm.class)
                .getResultList();
    }
}
