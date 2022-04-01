package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.AclassController;
import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.Parent;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.dto.ClassDataDTO;
import fis.police.fis_police_server.dto.ClassInfoDTO;
import fis.police.fis_police_server.dto.ClassSaveRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.service.interfaces.AclassService;
import fis.police.fis_police_server.service.interfaces.CenterService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
@Slf4j
public class AclassControllerImpl implements AclassController {

    private final AclassService aclassService;
    private final TokenService tokenService;
    private final CenterService centerService;

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

    // 교실 접속
    @Override
    @GetMapping("/class")
    public ClassInfoDTO list(@RequestParam Long class_id, HttpServletRequest request) throws IllegalAccessException {
        Aclass aclass = aclassService.findById(class_id);
//        if (! 원장) {
//            if (직원의 교실 != 접속하려는 교실) {
//                throw new IllegalAccessException("접근 불가")
//            }
//        }
        return aclassService.getClassInfo(aclass);
    }


}
