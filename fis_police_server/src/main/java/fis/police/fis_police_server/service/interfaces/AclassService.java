package fis.police.fis_police_server.service.interfaces;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.ClassDataDTO;
import fis.police.fis_police_server.dto.ClassInfoDTO;
import fis.police.fis_police_server.dto.ClassSaveRequest;

import java.util.List;

public interface AclassService {
    void save(ClassSaveRequest request, Center center, Officials officials);
    Aclass findById(Long id);
    ClassInfoDTO getClassInfo(Aclass aclass);
    List<ClassDataDTO> classByCenter(Center center);
}
