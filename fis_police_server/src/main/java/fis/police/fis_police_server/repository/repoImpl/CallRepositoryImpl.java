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

    @Override
    public Call findRecentDateByCenter(Long center_id) {

        return null;
    }

    @Override
    public Call findRecentByCenter(Long center_id) {

//        return em.createQuery("select c from Call c join fetch c.center ce where ce.id = : center_id and c.dateTime = (select max(c.dateTime) from Call c)", Call.class)
//                .setParameter("center_id", center_id)
//                .getSingleResult();
//        센터 별 콜 기록 중 최근 값 찾아오기 (primary key가 가장 큰 거 찾아오면 될 듯함) 가장 최근에 저장된 콜 기록에서 메일 주소를 따오기 위함
//        em.createQuery("select c from Call c where ")
        return null;

    }
}
