package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.QCenter;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.queryMethod.CenterQueryMethod;
import fis.police.fis_police_server.service.exceptions.CustomSearchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CenterRepositoryImpl extends CenterQueryMethod implements CenterRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;
    QCenter qCenter = QCenter.center;

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

    /*
        날짜 : 2022/01/11 1:58 오후
        작성자 : 현승구
        작성내용 : querydsl이용해서 centersearch 구현
    */
    @Override
    public List<SearchCenterResponseDTO> findBSearchCenterDTO(String c_name, String c_address, String c_ph) throws CustomSearchException {
        List<SearchCenterResponseDTO> centerList;
        if(c_name == null && c_address == null && c_ph == null){
            centerList =
                    jpaQueryFactory
                            .select(Projections.constructor(SearchCenterResponseDTO.class,
                                    qCenter.id, qCenter.c_address, qCenter.c_ph, qCenter.participation, qCenter.visited))
                            .from(qCenter)
                            .limit(1000)
                            .fetch();
        }
        else {
            centerList =
                    jpaQueryFactory
                            .select(Projections.constructor(SearchCenterResponseDTO.class,
                                    qCenter.id, qCenter.c_address, qCenter.c_ph, qCenter.participation, qCenter.visited))
                            .from(qCenter)
                            .where(cNameLike(c_name)
                                    .and(cAddressLike(c_address))
                                    .and(cPhLike(c_ph)))
                            .limit(1000)
                            .fetch();
        }
        if(centerList.isEmpty()) throw new CustomSearchException("findBSearchCenterDTO" ,"조건에 맞는 center가 존재하지 않습니다");
        else return centerList;
    }
}
