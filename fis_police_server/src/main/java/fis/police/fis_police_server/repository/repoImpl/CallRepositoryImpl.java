package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.repository.CallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/*
    작성 날짜: 2022/01/10 10:17 오전
    작성자: 고준영
    작성 내용: call repository impl 기본 메서드 구현
*/
@Repository
@RequiredArgsConstructor
public class CallRepositoryImpl implements CallRepository {

    private final EntityManager em;

    @Override
    public void save(Call call) {
        em.persist(call);
    }

    @Override
    public Call findById(Long id) {

        Call findCallById = em.find(Call.class, id);
        return findCallById;
    }
}
