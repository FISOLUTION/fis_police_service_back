package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Agent;

import java.util.List;

public interface AgentRepository {
    Agent findById(Long id);

    List<Agent> findAll();

    void save();

    void update();

    void delete();

}
