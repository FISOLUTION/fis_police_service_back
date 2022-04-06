package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Announce;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.AnnounceDeleteRequest;
import fis.police.fis_police_server.dto.AnnounceListDTO;
import fis.police.fis_police_server.dto.AnnounceModifyRequest;
import fis.police.fis_police_server.dto.AnnounceSaveRequest;
import fis.police.fis_police_server.repository.interfaces.AclassRepository;
import fis.police.fis_police_server.repository.interfaces.AnnounceRepository;
import fis.police.fis_police_server.service.interfaces.AnnounceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *    날짜 : 2022/04/06 2:46 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항
 */

@Service
@RequiredArgsConstructor
@Transactional
public class AnnounceServiceImpl implements AnnounceService {
    private final AnnounceRepository announceRepository;
    private final AclassRepository aclassRepository;

    @Override
    public Announce saveAnnounce(Officials officials, AnnounceSaveRequest announceSaveRequest) {
        Aclass findAclass = aclassRepository.findById(announceSaveRequest.getAclass_id());
        Announce announce = Announce.createAnnounce(officials, findAclass, announceSaveRequest);
        announceRepository.save(announce);
        return announce;
    }

    @Override
    public Announce modifyAnnounce(AnnounceModifyRequest announceModifyRequest) {
        Announce announce = announceRepository.findById(announceModifyRequest.getAnnounce_id());
        Aclass findAclass = aclassRepository.findById(announceModifyRequest.getAclass_id());
        announce.updateAnnounce(findAclass, announceModifyRequest);
        return announce;
    }

    @Override
    public Announce deleteAnnounce(AnnounceDeleteRequest announceDeleteRequest) {
        Announce announce = announceRepository.findById(announceDeleteRequest.getAnnounce_id());
        announce.deleteAnnounce(announceDeleteRequest);
        return announce;
    }

    @Override
    public Announce findById(Long id) {
        return announceRepository.findById(id);
    }

    @Override
    public List<AnnounceListDTO> findAll() {
        return announceRepository.findAll();
    }
}
