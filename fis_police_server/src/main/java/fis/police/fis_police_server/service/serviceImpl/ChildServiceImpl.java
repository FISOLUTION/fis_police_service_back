package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Child;
import fis.police.fis_police_server.domain.Parent;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.dto.ChildModifyRequest;
import fis.police.fis_police_server.dto.ChildSaveRequest;
import fis.police.fis_police_server.repository.interfaces.AclassRepository;
import fis.police.fis_police_server.repository.interfaces.CenterRepository;
import fis.police.fis_police_server.repository.interfaces.ChildRepository;
import fis.police.fis_police_server.service.interfaces.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final AclassRepository aclassRepository;
    private final CenterRepository centerRepository;

    @Override
    public void save(ChildSaveRequest request, Parent parent) {
        Aclass aclass = aclassRepository.findById(request.getClass_id());
        Child child = Child.createChild(request, parent, aclass);
        childRepository.save(child);
    }

    @Override
    public void modify(ChildModifyRequest request) {
        Child child = childRepository.findById(request.getChild_id());
        Aclass aclass = aclassRepository.findById(request.getClass_id());
        if (child.getAclass() != aclass) {
            child.modifyChild(request, aclass);
            child.acceptChild(Accept.TBD);
        }
        child.modifyChild(request, aclass);
    }

    @Override
    public void acceptChild(Long id, Accept accept) {
        childRepository.acceptChild(id, accept);
    }

    @Override
    public Child findById(Long id) {
        return childRepository.findById(id);
    }
}
