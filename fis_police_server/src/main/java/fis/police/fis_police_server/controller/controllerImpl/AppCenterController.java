package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.CenterDataResponse;
import fis.police.fis_police_server.dto.CenterSearchDTO;
import fis.police.fis_police_server.dto.CenterSearchResponseDTO;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.service.interfaces.AclassService;
import fis.police.fis_police_server.service.interfaces.CenterService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class AppCenterController {

    private final CenterService centerService;
    private final TokenService tokenService;
    private final AclassService aclassService;

    // 여기서 만약 오류가 생긴다면, Result<어쩌구> 이거 지우고 그냥 Result 로 하셈
    @GetMapping("/center/search")
    public Result<List<CenterSearchResponseDTO>> searchCenter(@RequestParam @Nullable String c_name, @RequestParam @Nullable String c_address, @RequestParam @Nullable String c_ph){
        log.info("[url : {}] [요청 : 시설 검색]", "/app/center/search");
        try {
            CenterSearchDTO centerSearchDTO = new CenterSearchDTO(c_name, c_address, c_ph);
            List<CenterSearchResponseDTO> results = centerService.findCenterList(c_name, c_address, c_ph);
            return new Result<>(results);
        } catch (NoResultException noResultException){
            return new Result<>(new ArrayList<CenterSearchResponseDTO>());
//            throw new NoResultException(noResultException.getMessage());
        } catch (NullPointerException nullPointerException){
//            logging(request, "/center/search", "null pointer exception 발생", false);
//            throw new NullPointerException("null pointer exception 발생");
            return null;
        }
    }

    // 내 시설에 접속 -> 시설 정보 + 교실 정보 + 직원 정보
    @GetMapping("/center")
    public CenterDataResponse getCenterData(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorization);
        log.info("[로그인 id값: {}] [url: {}] [요청: 시설 담당자 -> 시설 화면 접속, 시설 관련 정보 조회]", officialFromRequest.getId(), "center/by/official");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorization).get("role"));
        try {
            Center center = officialFromRequest.getCenter();
            return centerService.getCenterData(center);
        } catch (NullPointerException e) {
            throw new NullPointerException("NoCenter");
        }
    }

    // 시설별 교실 정보
    @GetMapping("/class/by/center")
    public Result classByCenter(@RequestParam Long center_id, HttpServletRequest request) throws IllegalAccessException {
        Center center = centerService.findById(center_id);
        return new Result(aclassService.classByCenter(center));
    }
}
