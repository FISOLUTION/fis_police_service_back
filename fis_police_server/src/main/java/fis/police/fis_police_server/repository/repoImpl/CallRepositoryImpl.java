package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.repository.CallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<Call> findAll() {
        return em.createQuery("select c from Call c", Call.class)
                .getResultList();
    }

/*
    작성 날짜: 2022/01/12 3:09 오후
    작성자: 고준영
    작성 내용: 위의 findAll 함수에 date 변수를 삽입한 것, LocalDateTime으로 되어있는 값을 갖고 노는 방법 공부 후 이걸로 findAll 대체 예정
*/
    @Override
    public List<Call> testDate(String date) {
        return em.createQuery("select c from Call c where c.dateTime = : date", Call.class)
                .setParameter("date", date)
                .getResultList();
    }

}
