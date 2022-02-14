package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Hope;
import fis.police.fis_police_server.repository.HopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/*
    작성 날짜: 2022/02/14 11:46 오전
    작성자: 고준영
    작성 내용: 지문등록 신청서 저장, 조회
*/
@Repository
@RequiredArgsConstructor
public class HopeRepositoryImpl implements HopeRepository {

    private final EntityManager em;

    @Override
    public void saveHope(Hope hope) {
        em.persist(hope);
    }

    @Override
    public void listOfHope() {

    }
}
