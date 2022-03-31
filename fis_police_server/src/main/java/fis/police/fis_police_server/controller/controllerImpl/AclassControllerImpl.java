package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.AclassController;
import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.ClassSaveRequest;
import fis.police.fis_police_server.service.interfaces.AclassService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
@Slf4j
public class AclassControllerImpl implements AclassController {

    private final AclassService aclassService;
    private final TokenService tokenService;

    // 시설에 학급 추가
    @Override
    @PostMapping("/class")
    public void save(@RequestBody ClassSaveRequest request, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorization);
        log.info("[로그인 id값: {}] [url: {}] [요청: 교실 추가]", officialFromRequest.getId(), "class");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorization).get("role"));
        try {
            Center center = officialFromRequest.getCenter();
            aclassService.save(request, center, officialFromRequest);
        } catch (NullPointerException e) {
            throw new NullPointerException("NoCenter");
        }
    }

    @Override
    public void delete(Long id) {

    }

    // 교실 정보??
    @GetMapping("/class")
    public void list(@RequestParam Long class_id, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorization);
        log.info("[로그인 id 값: {}] [url: {}] [요청: 교실 페이지 접속 -> 교실 정보]", officialFromRequest.getId(), "/class?class_id=" + class_id);
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorization).get("role"));
        Center center = officialFromRequest.getCenter();
        Aclass aclass = officialFromRequest.getAclass();
//        setting controller 에 있는 걸 가져다가 복붙해서 aclass service 에 붙여야하나? 하는 의문...
//        시설 담당자가 맡은 학급과 접속을 원하는 학급이 일치하는 지도 점검해야하나?????
//        그러면 시설 담당자가 회원가입할 때, 그걸 해야겠네
//        학급 아이디 적어서 제출하면 그걸로 엮도록,,,
//        OFFICIAL 인 경우는 말고, TEACHER 인 경우는 엮고...
//        시벌 맞나???
    }
}
