package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Parent;

import java.util.List;

public interface ParentRepository {

    void save(Parent parent);
    Parent findById(Long id);
    List<Parent> findByNickname(String nickname);

}
