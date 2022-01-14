package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Agent;

import java.time.LocalDate;
import java.util.List;

//이승범
public interface AgentRepository {
    Agent findById(Long id);

    public List<Agent> findByA_code(String a_code);

    List<Agent> findAll();

    void save(Agent agent);

    List<Agent> findNearAgent(Double latitude, Double longitude, Long range, LocalDate visit_date);
}
