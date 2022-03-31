package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Child;
import fis.police.fis_police_server.domain.enumType.Accept;

public interface ChildRepository {
    void save(Child child);
    Child findById(Long id);
    void acceptChild(Long id, Accept accept);
}
