package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.SettingController;
import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/app")
public class SettingControllerImpl implements SettingController {

    private final TokenService tokenService;
    private final OfficialService officialService;
    private final AgentService agentService;
    private final ParentService parentService;
    private final BoardService boardService;
    // private final AnnounceService announceService;
    private final ChildService childService;
    private final AclassService aclassService;
    private final CenterService centerService;

    @Override
    @GetMapping("/official/setting")
    public Object basicOfficialInfo(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
        log.info("[로그인 id 값 : {}] [url : {}] [요청 : 시설 담당자 기본 정보]", officialFromRequest.getId(), "/official/setting");
        try {
            Officials official = officialService.findById(officialFromRequest.getId());
            Center center = official.getCenter();
            return new SettingOfficialDTO(official.getId(), center.getId(), center.getC_name(), center.getC_address(), official.getO_name(), official.getO_ph(), official.getO_email(), official.getO_nickname(), official.getO_pwd(), official.getAccept());
        } catch (NullPointerException e) {
            throw new NullPointerException("NoOfficial");
        }
    }

    @Override
    @GetMapping("/agent/setting")
    public Object basicAgentInfo(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        Agent agentFromRequest = tokenService.getAgentFromRequest(authorizationHeader);
        log.info("[로그인 id 값 : {}] [url : {}] [요청 : 현장요원 기본 정보]", agentFromRequest.getId(), "/agent/setting");
        try {
            Agent agent = agentService.findById(agentFromRequest.getId());
            return new SettingAgentDTO(agent.getA_name(), agent.getA_nickname(), agent.getA_pwd(), agent.getA_ph());
        } catch (NullPointerException e) {
            throw new NullPointerException("NoAgent");
        }
    }

    @Override
    @GetMapping("/parent/setting")
    public Object basicParentInfo(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        Parent parentFromRequest = tokenService.getParentFromRequest(authorization);
        log.info("[로그인 id 값 : {}] [url : {}] [요청 : 학부모 기본 정보]", parentFromRequest.getId(), "/parent/setting");
        try {
            Parent parent = parentService.findById(parentFromRequest.getId());
            SettingParentDTO settingParentDTO = new SettingParentDTO(parent.getName(), parent.getPh(), parent.getEmail());
            List<Child> childList = parent.getChildList();
            List<ChildListDTO> children = childList.stream()
                    .map(child -> new ChildListDTO(child.getId(), child.getName(), child.getBirthday(),
                            new CenterNameDTO(child.getAclass().getCenter().getId(), child.getAclass().getCenter().getC_name()),
                            new ClassDataDTO(child.getAclass().getId(), child.getAclass().getName()),
                            child.getAccept()))
                    .collect(Collectors.toList());
            settingParentDTO.setChildren(children);
            return settingParentDTO;
        } catch (NullPointerException e) {
            throw new NullPointerException("NoParent");
        }
    }
}
