package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.OfficialsController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.interfaces.CenterService;
import fis.police.fis_police_server.service.interfaces.OfficialService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    작성 날짜: 2022/02/14 11:35 오전
    작성자: 고준영
    작성 내용: 시설 담당자 회원가입
*/
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class OfficialsControllerImpl implements OfficialsController {

    private final OfficialService officialService;
    private final TokenService tokenService;
    private final CenterService centerService;

    @Override
    @PostMapping("/officials")
    public WellDoneResponse saveOfficials(@RequestBody OfficialSaveRequest request) {
        log.info("[url: {}] [요청: 시설 담당자 회원가입]", "/officials");
        Center center = centerService.findById(request.getCenter_id());
        officialService.saveOfficials(request, center);
        return new WellDoneResponse("200", "created");
    }

    @Override
    @PatchMapping("/officials")
    public WellDoneResponse modifyOfficials(@RequestBody OfficialSaveRequest request, HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        log.info("[로그인 id값: {}] [url: {}] [요청: 시설 담당자 정보 수정]", tokenService.getOfficialFromRequest(authorizationHeader).getId(), "/officials");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        if (officialFromRequest.getAccept() == Accept.REJECT) {
            throw new IllegalStateException("승인되지 않은 사용자입니다.");
        }
        Center center = centerService.findById(request.getCenter_id());
        officialService.modifyOfficials(officialFromRequest, request, center);
        return new WellDoneResponse("200", "updated");
    }

    // 내 시설에 아직 승인되지 않은 직원 리스트 (TBD)
    @GetMapping("/officials/accept")
    public List<OfficialDTO> waitingOfficial(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorization);
        Long center_id = officialFromRequest.getCenter().getId();
        return officialService.findOfficialsWaitingAccept(center_id);
    }

    // 시설 직원 승인
    @PostMapping("/officials/accept")
    public void acceptOfficial(@RequestBody AcceptOfficialDTO officialDTO, HttpServletRequest request) throws IllegalAccessException {
        String authorization = request.getHeader("Authorization");
        if (!tokenService.parseJwtToken(authorization).get("role").toString().equals("OFFICIAL")) {
            throw new IllegalAccessException("접근이 허용되지 않은 사용자입니다.");
        }
        officialService.acceptOfficial(officialDTO.getOfficial_id(), officialDTO.getAccept());
    }

    // 시설 담당자가 맡을 반 연결해주기
    @PostMapping("/officials/class")
    public void mappingClass(HttpServletRequest request, @RequestBody ClassForOfficialDTO classForOfficialDTO) throws IllegalAccessException {
        String authorization = request.getHeader("Authorization");
        if (!tokenService.parseJwtToken(authorization).get("role").toString().equals("OFFICIAL")) {
            throw new IllegalAccessException("접근이 허용되지 않은 사용자입니다.");
        }
        officialService.mappingClass(classForOfficialDTO.getOfficial_id(), classForOfficialDTO.getClass_id());
    }
}
