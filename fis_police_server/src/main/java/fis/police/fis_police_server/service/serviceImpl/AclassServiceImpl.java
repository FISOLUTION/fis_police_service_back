package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.ClassSaveRequest;
import fis.police.fis_police_server.repository.interfaces.AclassRepository;
import fis.police.fis_police_server.service.interfaces.AclassService;
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

    public void getInfo(Center center, Aclass aclass, Officials officials) throws IllegalAccessException {
        Aclass byId = this.findById(officials.getAclass().getId());
        validateClass(aclass, byId);

    }

    private void validateClass(Aclass aclass, Aclass byId) throws IllegalAccessException {
        if (!aclass.equals(byId)) {
            throw new IllegalAccessException("접근이 허용되지 않은 교사입니다.");
        }
    }
}
