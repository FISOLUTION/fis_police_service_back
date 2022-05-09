package fis.police.fis_police_server.service.interfaces;

import fis.police.fis_police_server.domain.Announce;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.AnnounceDeleteRequest;
import fis.police.fis_police_server.dto.AnnounceListDTO;
import fis.police.fis_police_server.dto.AnnounceModifyRequest;
import fis.police.fis_police_server.dto.AnnounceSaveRequest;

import java.util.List;

/**
 *    날짜 : 2022/04/06 2:45 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항
 */
public interface AnnounceService {
    //공지사항 게시물 추가
    Announce saveAnnounce(Officials officials, AnnounceSaveRequest announceSaveRequest);

    //공지사항 게시물 수정
    Announce modifyAnnounce(AnnounceModifyRequest announceModifyRequest);

    //공지사항 게시물 삭제
    Announce deleteAnnounce(AnnounceDeleteRequest announceDeleteRequest);

    //공지사항 하나 조회
    Announce findById(Long id);

    //모든 일정 조회
    List<AnnounceListDTO> findAll();

}
