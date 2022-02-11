package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Confirm;

import java.util.List;

public interface ConfirmRepository {

    void save(Confirm confirm);

    List<Confirm> findAll();

}
