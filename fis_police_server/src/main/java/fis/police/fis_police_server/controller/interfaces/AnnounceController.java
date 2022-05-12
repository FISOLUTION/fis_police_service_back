package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.AnnounceDeleteRequest;
import fis.police.fis_police_server.dto.AnnounceListDTO;
import fis.police.fis_police_server.dto.AnnounceModifyRequest;
import fis.police.fis_police_server.dto.AnnounceSaveRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *    날짜 : 2022/04/06 2:53 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항
 */
public interface AnnounceController {
    //게시글 추가
    void saveAnnounce(AnnounceSaveRequest announceSaveRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;


    //게시글 수정
    void modifyAnnounce(AnnounceModifyRequest announceModifyRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;


    //게시글 삭제 컬럼 업데이트
    void deleteAnnounce(AnnounceDeleteRequest announceDeleteRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;


    //조회
    List<AnnounceListDTO> getAnnounce(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException;
}
