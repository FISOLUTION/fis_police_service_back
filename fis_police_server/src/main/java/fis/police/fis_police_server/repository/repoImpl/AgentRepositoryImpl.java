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
    public List<Agent> findAll() {
        return em.createQuery("select a from Agent a", Agent.class)
                .getResultList();
    }

    @Override
    public void save(Agent agent) {
        em.persist(agent);
    }

}
