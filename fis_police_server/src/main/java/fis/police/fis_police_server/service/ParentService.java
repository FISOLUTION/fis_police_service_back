package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Parent;
import fis.police.fis_police_server.dto.AddChildRequest;
import fis.police.fis_police_server.dto.ParentSaveRequest;

public interface ParentService {
    void save(ParentSaveRequest request);
    void addChild(AddChildRequest request);
    Parent findById(Long id);
}
