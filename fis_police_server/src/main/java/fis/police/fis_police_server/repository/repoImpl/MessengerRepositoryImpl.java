package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Messenger;
import fis.police.fis_police_server.repository.MessengerRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class MessengerRepositoryImpl implements MessengerRepository {

    private final EntityManager em;

    @Override
    public void save(Messenger messenger) {
        em.persist(messenger);
    }

    @Override
    public Messenger findById(Long id) {
        return em.find(Messenger.class, id);
    }

    @Override
    public void delete(Messenger messenger) {
        Long id = messenger.getId();

        em.createQuery("delete from Messenger where Messenger.id = :id")
                .setParameter("id", id);
    }
}
