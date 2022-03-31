package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.ClassSaveRequest;
import fis.police.fis_police_server.repository.AclassRepository;
import fis.police.fis_police_server.service.AclassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AclassServiceImpl implements AclassService {

    private final AclassRepository aclassRepository;

    @Override
    @Transactional
    public void save(ClassSaveRequest request, Center center, Officials officials) {
        Aclass aClass = Aclass.createClass(request, center, officials);
        aclassRepository.save(aClass);
    }

    @Override
    public Aclass findById(Long id) {
        return aclassRepository.findById(id);
    }
}
