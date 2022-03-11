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
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficialServiceImpl implements OfficialService {

    private final OfficialsRepository officialsRepository;
    private final CenterRepository centerRepository;
    private final CheckNicknameService nicknameService;

    @Override
    public void saveOfficials(OfficialSaveRequest request, Center center) {
//        nicknameService.CheckNicknameOverlap(request.getO_nickname());
        checkDuplicateByNickname(request.getO_nickname());
        Officials officials = Officials.createOfficials(request, center);
        officialsRepository.saveOfficials(officials);
    }

    @Override
    public void modifyOfficials(Officials officialFromRequest, OfficialSaveRequest request, Center center) {
//        nicknameService.CheckNicknameOverlap(request.getO_nickname());
        officialFromRequest.modifyOfficial(request, center);
    }

    @Override
    public Center findCenter(Long id) {
        return centerRepository.findById(id);
    }

    private void checkDuplicateByNickname(String nickname) {
        List<Officials> byNickname = officialsRepository.findByNickname(nickname);
        if (!byNickname.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }
}
