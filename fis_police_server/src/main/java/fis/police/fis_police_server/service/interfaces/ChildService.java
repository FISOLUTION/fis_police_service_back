package fis.police.fis_police_server.service.interfaces;

import fis.police.fis_police_server.domain.Parent;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.dto.ChildModifyRequest;
import fis.police.fis_police_server.dto.ChildSaveRequest;

public interface ChildService {
    void save(ChildSaveRequest request, Parent parent);
    void modify(ChildModifyRequest request);
    void acceptChild(Long id, Accept accept);
}
