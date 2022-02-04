package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/*
    작성날짜: 2022/01/10 10:56 AM
    작성자: 이승범
    작성내용: AgentRepositoryImpl 생성
*/
@Repository
@RequiredArgsConstructor
public class AgentRepositoryImpl implements AgentRepository {

    private final EntityManager em;

    @Override
    public Agent findById(Long id) {
        return em.find(Agent.class, id);
    }

    @Override
    public List<Agent> findByA_code(String a_code) {
        return em.createQuery("select a from Agent a where a.a_code =:a_code", Agent.class)
                .setParameter("a_code", a_code)
                .getResultList();
    }

    @Override
    public List<Agent> findAll() {
        return em.createQuery("select a from Agent a", Agent.class).getResultList();
    }

    @Override
    public void save(Agent agent) {
        em.persist(agent);
    }

    @Override
    public List<Agent> findNearAgent(Double latitude, Double longitude, Long range) {
        Double latitude_l = latitude - (0.009D * range);
        Double latitude_h = latitude + (0.009D * range);
        Double longitude_l = longitude - (0.009D * range);
        Double longitude_h = longitude + (0.009D * range);
        return em.createQuery
                ("select distinct agent from Agent agent " +
                        "left join fetch agent.scheduleList as schedule " +
                        "left join fetch schedule.center " +
                        "where agent.a_latitude < :latitude_h and agent.a_latitude > :latitude_l " +
                        "and agent.a_longitude < :longitude_h and agent.a_longitude > :longitude_l " +
                        "and agent.a_status = :a_status", Agent.class)
                    .setParameter("latitude_h", latitude_h)
                    .setParameter("latitude_l", latitude_l)
                    .setParameter("longitude_l", longitude_l)
                    .setParameter("longitude_h", longitude_h)
                    .setParameter("a_status", AgentStatus.WORK)
                    .getResultList();
    }

}


//    select * from Agent where a_latitude < h and a_latitude > l and a_longitude < h and a_longitude > l and a_status = "WORK"