package fis.police.fis_police_server.repository.repoImpl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.QCall;
import fis.police.fis_police_server.domain.QCenter;
import fis.police.fis_police_server.domain.QSchedule;
import fis.police.fis_police_server.dto.CenterSearchResponseDTO;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.queryMethod.CenterQueryMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CenterRepositoryImpl extends CenterQueryMethod implements CenterRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;
    QCenter qCenter = QCenter.center;
    QCall qCall = QCall.call;
    QSchedule qSchedule = QSchedule.schedule;

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
    public List<CenterSearchResponseDTO> findBySearchCenterDTO(String c_name, String c_address, String c_ph) {
        List<CenterSearchResponseDTO> centerList;
        if(c_name == null && c_address == null && c_ph == null){
            centerList =
                    jpaQueryFactory
                            .select(Projections.constructor(CenterSearchResponseDTO.class,
                                    qCenter.id, qCenter.c_address, qCenter.c_ph, qCenter.participation, qCenter.visited))
                            .from(qCenter)
                            .limit(1000)
                            .fetch();
        }
        else {
            centerList =
                    jpaQueryFactory
                            .select(Projections.constructor(CenterSearchResponseDTO.class,
                                    qCenter.id, qCenter.c_address, qCenter.c_ph, qCenter.participation, qCenter.visited))
                            .from(qCenter)
                            .where(cNameLike(c_name)
                                    .and(cAddressLike(c_address))
                                    .and(cPhLike(c_ph)))
                            .limit(1000)
                            .fetch();
        }
        return centerList;
    }

    @Override
    public Center findByIdAndFetchAll(Long id) throws NoResultException, NonUniqueResultException {
        //  list "select center from Center center join Center.Call on 조건 join
        return em.createQuery("select distinct center from Center center " +
                "left join fetch center.callList as call " +
                "join fetch call.user " +
                "where center.id = :id ", Center.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    @Override
    public List<Center> findNearCenter(double latitude, double longitude) {
        double latitude_l = latitude - 0.009;
        System.out.println("latitude_l = " + latitude_l);
        double latitude_h = latitude + 0.009D;
        System.out.println("latitude_h = " + latitude_h);
        double longitude_l = longitude - 0.009;
        System.out.println("longitude_l = " + longitude_l);
        double longitude_h = longitude + 0.009;
        System.out.println("longitude_h = " + longitude_h);

        List<Center> centerList = em.createQuery("select distinct center " +
                "from Center center " +
                "where center.c_latitude <= :latitude_h and center.c_latitude >= :latitude_l " +
                "and center.c_longitude <= :longitude_h and center.c_longitude >= :longitude_l", Center.class)
                .setParameter("latitude_h", latitude_h)
                .setParameter("latitude_l", latitude_l)
                .setParameter("longitude_l", longitude_l)
                .setParameter("longitude_h", longitude_h)
                .getResultList();

        System.out.println("centerList.toString() = " + centerList.toString());
        return centerList;
    }
}
