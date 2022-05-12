package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.repository.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


/*
    날짜 : 2022/01/10 10:27 오전
    작성자 : 원보라
    작성내용 : user repository impl 기본 메서드 구현
*/
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User findById(Long id) {
        User findUserById = em.find(User.class, id);
        return findUserById;
    }

    @Override
    public List<User> findByNickname(String nickname) {
        return em.createQuery("select u from User u where u.u_nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public List<Call> findUserAndCallByDate(String date, Long userId) {
        return em.createQuery("select c " +
                        "from Call c left join c.user u " +
                        "where c.date = :date and u.id = :userId", Call.class)
                .setParameter("date", date)
                .setParameter("userId", userId)
                .getResultList();
    }

}
