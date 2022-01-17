package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Messenger;
import fis.police.fis_police_server.repository.MessengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/*
    작성날짜: 2022/01/10 10:57 AM
    작성자: 현승구
    작성내용: MessengerRepositoryImpl 생성
*/
@Repository
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

    @Override
    public List<Messenger> findAll() {
        return em.createQuery("select messenger from Messenger messenger " +
                "join fetch messenger.user ", Messenger.class)
                .getResultList();
    }
}
