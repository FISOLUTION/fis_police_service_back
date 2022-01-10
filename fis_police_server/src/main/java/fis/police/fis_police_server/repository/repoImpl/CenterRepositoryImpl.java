package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.repository.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CenterRepositoryImpl implements CenterRepository {

    private final EntityManager em;

    /*
        날짜 : 2022/01/10 10:42 오전
        작성자 : 현승구
        작성내용 : 시설 저장 삭제 검색(id)
    */
    @Override
    public void save(Center center) {
        em.persist(center);
    }

    @Override
    public Center findById(Long id) {
        return em.find(Center.class, id);
    }

    @Override
    public void delete(Center center) {
        Long id = center.getId();
        em.createQuery("delete from Center where Center.id = :id")
                .setParameter("id", id);
    }
}
