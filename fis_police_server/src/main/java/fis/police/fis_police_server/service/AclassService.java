package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.ClassSaveRequest;

public interface AclassService {
    void save(ClassSaveRequest request, Center center, Officials officials);
    Aclass findById(Long id);
}
