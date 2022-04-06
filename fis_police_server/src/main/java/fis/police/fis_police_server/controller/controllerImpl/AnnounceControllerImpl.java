package fis.police.fis_police_server.controller.controllerImpl;


import fis.police.fis_police_server.controller.interfaces.AnnounceController;
import fis.police.fis_police_server.domain.Announce;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.AnnounceDeleteRequest;
import fis.police.fis_police_server.dto.AnnounceListDTO;
import fis.police.fis_police_server.dto.AnnounceModifyRequest;
import fis.police.fis_police_server.dto.AnnounceSaveRequest;
import fis.police.fis_police_server.service.interfaces.AnnounceService;
import fis.police.fis_police_server.service.interfaces.OfficialService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 날짜 : 2022/04/06 2:55 오후
 * 작성자 : 원보라
 * 작성내용 : 공지사항
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/app")
public class AnnounceControllerImpl implements AnnounceController {
    private final AnnounceService announceService;
    private final TokenService tokenService;
    private final OfficialService officialService;

    /**
     * 공지사항 추가
     *
     * @param announceSaveRequest
     * @param httpServletRequest
     * @param response
     * @throws IOException
     */
    @Override
    @PostMapping("/announce")
    public void saveAnnounce(@RequestBody AnnounceSaveRequest announceSaveRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        Officials findOfficial = officialService.findById(officialFromRequest.getId());
        log.info("[로그인 id값: {}] [url: {}] [요청: 공지사 일정 추가]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/announce");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            announceService.saveAnnounce(findOfficial, announceSaveRequest);            //성공시 200 ok
            log.info("공지사항 일정 추가 완료");
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoAnnounce");
        }
    }

    /**
     * 공지사항 수정
     *
     * @param announceModifyRequest
     * @param httpServletRequest
     * @param response
     * @throws IOException
     */
    @Override
    @PatchMapping("/announce")
    public void modifyAnnounce(@RequestBody AnnounceModifyRequest announceModifyRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        log.info("[로그인 id값: {}] [url: {}] [요청: 공지사항 일정 수정]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/announce");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            Announce announce = announceService.findById(announceModifyRequest.getAnnounce_id());
            if (officialFromRequest.getId().equals(announce.getOfficials().getId())) { //일정을 작성한 official 맞을 경우 수정 가능
                announceService.modifyAnnounce(announceModifyRequest);            //성공시 200 ok
                log.info("공지사항 일정 수정 완료");
            } else {
                throw new IllegalArgumentException("DifferentOfficial"); //다른 사람이 수정하려 할 때
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoAnnounce");
        }
    }

    /**
     * 공지사항 삭제
     *
     * @param announceDeleteRequest
     * @param httpServletRequest
     * @param response
     * @throws IOException
     */
    @Override
    @PatchMapping("/announce/delete")
    public void deleteAnnounce(@RequestBody AnnounceDeleteRequest announceDeleteRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        log.info("[로그인 id값: {}] [url: {}] [요청: 공지사항 일정 삭제]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/app/announce/delete");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorizationHeader).get("role"));
        try {
            Announce announce = announceService.findById(announceDeleteRequest.getAnnounce_id());
            if (officialFromRequest.getId().equals(announce.getOfficials().getId())) { //작성한 official 맞을 경우 수정 가능
                announceService.deleteAnnounce(announceDeleteRequest);            //성공시 200 ok
                log.info("공지사항 일정 삭제 업데이트");
            } else {
                throw new IllegalArgumentException("DifferentOfficial"); //다른 사람이 삭제하려 할 때
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoAnnounce");
        }
    }

    /**
     * 공지사항 조회
     *
     * @param httpServletRequest
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    @GetMapping("/announce")
    public List<AnnounceListDTO> getAnnounce(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        try {
            return announceService.findAll();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        }
    }
}
