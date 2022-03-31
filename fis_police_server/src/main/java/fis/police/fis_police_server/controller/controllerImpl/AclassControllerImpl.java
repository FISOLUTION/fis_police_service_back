package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AclassController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.ClassSaveRequest;
import fis.police.fis_police_server.service.AclassService;
import fis.police.fis_police_server.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
@Slf4j
public class AclassControllerImpl implements AclassController {

    private final AclassService aclassService;
    private final TokenService tokenService;

    @Override
    @PostMapping("/class")
    public void save(@RequestBody ClassSaveRequest request, HttpServletRequest httpServletRequest) {
        if (request.getClass_id() == null) {
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
        } else {

        }
    }

    @Override
    public void delete(Long id) {

    }
}
