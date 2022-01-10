package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


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
}
