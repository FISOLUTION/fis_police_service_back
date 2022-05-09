package fis.police.fis_police_server.repository.interfaces;


import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Center;

import java.util.List;

/**
 * 2022/03/29/ 11:06 오전
 * 원보라
 * 유치원 - 반
 */
public interface AclassRepository {
    void save(Aclass aclass);

    Aclass findById(Long id);

    List<Aclass> findAll();

    List<Aclass> classByCenter(Center center);
}

