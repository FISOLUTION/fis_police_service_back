package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ChildController;
import fis.police.fis_police_server.domain.Parent;
import fis.police.fis_police_server.dto.AcceptChildDTO;
import fis.police.fis_police_server.dto.ChildModifyRequest;
import fis.police.fis_police_server.dto.ChildSaveRequest;
import fis.police.fis_police_server.service.ChildService;
import fis.police.fis_police_server.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class ChildControllerImpl implements ChildController {

    private final ChildService childService;
    private final TokenService tokenService;

    @Override
    @PostMapping("/child")
    public void save(@RequestBody ChildSaveRequest request, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        Parent parentFromRequest = tokenService.getParentFromRequest(authorization);
        log.info("[로그인 id값: {}] [url: {}] [요청: 부모 -> 아이 추가]", parentFromRequest.getId(), "child");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorization).get("role"));
        try {
            childService.save(request, parentFromRequest);
        } catch (NullPointerException e) {
            throw new NullPointerException("NoClass");
        }
    }

    @Override
    @PatchMapping("/child")
    public void modify(ChildModifyRequest request, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        Parent parentFromRequest = tokenService.getParentFromRequest(authorization);
        log.info("[로그인 id값: {}] [url: {}] [요청: 부모 -> 아이 추가]", parentFromRequest.getId(), "child");
        log.info("[로그인 역할: {}]", (String) tokenService.parseJwtToken(authorization).get("role"));

        childService.modify(request);
    }

    @Override
    @DeleteMapping("/child")
    public void delete(Long id) {

    }

    @Override
    @PostMapping("/child/accept")
    public void acceptChild(@RequestBody AcceptChildDTO childDTO, HttpServletRequest request) {
        log.info("[url: {}] [요청: 시설 담당자 -> 아이 승인]", "/child/accept");
        childService.acceptChild(childDTO.getChild_id(), childDTO.getAccept());
    }
}
