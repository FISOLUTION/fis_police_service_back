package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Child;
import fis.police.fis_police_server.domain.Parent;
import fis.police.fis_police_server.dto.AddChildRequest;
import fis.police.fis_police_server.dto.ParentSaveRequest;
import fis.police.fis_police_server.repository.AclassRepository;
import fis.police.fis_police_server.repository.ChildRepository;
import fis.police.fis_police_server.repository.ParentRepository;
import fis.police.fis_police_server.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final AclassRepository aclassRepository;
    private final ChildRepository childRepository;

    @Override
    public void save(ParentSaveRequest request) {
        validateDuplicateParent(request.getNickname());

        Parent parent = Parent.createParent(request);
        parentRepository.save(parent);
    }

    @Override
    public void addChild(AddChildRequest request) {

    }

    @Override
    public Parent findById(Long id) {
        return parentRepository.findById(id);
    }

    private void validateDuplicateParent(String nickname) {
        List<Parent> parent = parentRepository.findByNickname(nickname);
        if (!parent.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

}
