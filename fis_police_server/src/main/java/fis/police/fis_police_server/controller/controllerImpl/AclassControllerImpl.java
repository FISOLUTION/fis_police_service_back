package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.AclassController;
import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.interfaces.AclassService;
import fis.police.fis_police_server.service.interfaces.CenterService;
import fis.police.fis_police_server.service.interfaces.ChildService;
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
    private final CenterService centerService;
    private final ChildService childService;

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
    public ClassInfoDTO list(@RequestBody AccessClassDTO classDTO, HttpServletRequest request) throws IllegalAccessException {
        Aclass aclass = aclassService.findById(classDTO.getClass_id());
        String authorization = request.getHeader("Authorization");
        String role = tokenService.parseJwtToken(authorization).get("role").toString();
        if (role.equals("TEACHER")) {
            Officials officialFromRequest = tokenService.getOfficialFromRequest(authorization);
            if (!officialFromRequest.getAclass().equals(aclass)) {
                throw new IllegalAccessException("담당 교실이 아닌 교실 페이지로는 접속할 수 없습니다.");
            }
            System.out.println("officialFromRequest.getAccept() = " + officialFromRequest.getAccept());
            if (officialFromRequest.getAccept() != Accept.ACCEPT) {
                throw new IllegalAccessException("접속이 승인되지 않았습니다.");
            }
        } else if (role.equals("PARENT")) {
            Child child = childService.findById(classDTO.getChild_id());
            if (!child.getAclass().equals(aclass)) {
                throw new IllegalAccessException("담당 교실이 아닌 교실 페이지로는 접속할 수 없습니다.");
            }
            if (child.getAccept() != Accept.ACCEPT) {
                throw new IllegalAccessException("접속이 승인되지 않았습니다.");
            }
        }
        return aclassService.getClassInfo(aclass);
    }


}
