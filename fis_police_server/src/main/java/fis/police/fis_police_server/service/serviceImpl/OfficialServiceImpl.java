package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.AcceptOfficialDTO;
import fis.police.fis_police_server.dto.OfficialDTO;
import fis.police.fis_police_server.dto.OfficialSaveRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.OfficialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficialServiceImpl implements OfficialService {

    private final OfficialsRepository officialsRepository;

    @Override
    public void saveOfficials(OfficialSaveRequest request, Center center) {
//        nicknameService.CheckNicknameOverlap(request.getO_nickname());
        checkDuplicateByNickname(request.getO_nickname());
        if (request.getU_auth() == UserAuthority.OFFICIAL) {
            Officials officials = Officials.createOfficials(request, center, Accept.accept);
            officialsRepository.saveOfficials(officials);
        } else if (request.getU_auth() == UserAuthority.TEACHER) {
            Officials officials = Officials.createOfficials(request, center, Accept.TBD);
            officialsRepository.saveOfficials(officials);
        }
    }

    @Override
    public void modifyOfficials(Officials officialFromRequest, OfficialSaveRequest request, Center center) {
//        nicknameService.CheckNicknameOverlap(request.getO_nickname());
        officialFromRequest.modifyOfficial(request, center);
    }

    @Override
    public Officials findById(Long id) {
        try {
            return officialsRepository.findById(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("해당 시설 관계자 정보 없음.");
        }
    }

    private void checkDuplicateByNickname(String nickname) {
        List<Officials> byNickname = officialsRepository.findByNickname(nickname);
        if (!byNickname.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    @Override
    public void acceptOfficial(Long official_id, Accept accept) {
        officialsRepository.acceptOfficial(official_id, accept);
    }

    @Override
    public Result findOfficialsWaitingAccept(Long center_id) {
        List<Officials> officialsWaitingAccept = officialsRepository.findOfficialsWaitingAccept(center_id, Accept.TBD);
        List<OfficialDTO> collect = officialsWaitingAccept.stream()
                .map(official -> new OfficialDTO(official.getId(), official.getO_name(), official.getO_ph(), official.getO_email()))
                .collect(Collectors.toList());
        return new Result(collect);

    }

}
