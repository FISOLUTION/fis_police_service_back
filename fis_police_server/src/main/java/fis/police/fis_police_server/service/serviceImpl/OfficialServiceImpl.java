package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.OfficialSaveRequest;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.OfficialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficialServiceImpl implements OfficialService {

    private final OfficialsRepository officialsRepository;
    private final CenterRepository centerRepository;
    private final CheckNicknameService nicknameService;

    @Override
    public void saveOfficials(OfficialSaveRequest request, Center center) {
        nicknameService.CheckNicknameOverlap(request.getO_nickname());
        Officials officials = Officials.createOfficials(request, center);
        officialsRepository.saveOfficials(officials);
    }

    @Override
    public Center findCenter(Long id) {
        return centerRepository.findById(id);
    }
}
