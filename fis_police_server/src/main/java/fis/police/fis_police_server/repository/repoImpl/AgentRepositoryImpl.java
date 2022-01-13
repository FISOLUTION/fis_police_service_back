package fis.police.fis_police_server.repository.repoImpl;

import fis.police.fis_police_server.domain.Agent;
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

        Double latitude_l = latitude - (0.009F * range);
        Double latitude_h = latitude + (0.009F * range);

        Double longitude_l = longitude - (0.009F * range);
        Double longitude_h = longitude + (0.009F * range);

        return em.createQuery("select Agent " +
                "from Agent agent " +
                "where agent.a_latitude < :latitude_h and agent.a_latitude > :latitude_l " +
                "and agent.a_longitude < :longitude_h and agent.a_longitude > :longitude_l")
                .setParameter("latitude_h", latitude_h)
                .setParameter("latitude_l", latitude_l)
                .setParameter("longitude_l", longitude_l)
                .setParameter("longitude_h", longitude_h)
                .getResultList();
    }

}
