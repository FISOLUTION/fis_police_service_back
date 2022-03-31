package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Aclass;

public interface AclassRepository {
    void save(Aclass aclass);
    Aclass findById(Long id);

}
