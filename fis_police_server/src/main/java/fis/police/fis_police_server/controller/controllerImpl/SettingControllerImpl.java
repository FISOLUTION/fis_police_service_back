package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.SettingController;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.SettingAgentDTO;
import fis.police.fis_police_server.dto.SettingOfficialDTO;
import fis.police.fis_police_server.service.SettingService;
import fis.police.fis_police_server.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/app")
public class SettingControllerImpl implements SettingController {
    private final TokenService tokenService;
    private final SettingService settingService;

    @Override
    @GetMapping("/official/setting")
    public Object basicOfficialInfo(HttpServletRequest request) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
            Officials official = settingService.getOfficial(officialFromRequest.getId());
            return new SettingOfficialDTO(official.getId(), official.getCenter().getC_name(), official.getO_name(), official.getO_ph(), official.getO_email(), official.getO_nickname(), official.getO_pwd());
        } catch (NullPointerException e) {
            throw new NullPointerException("사용자 정보 없음.");
        }
    }

    @Override
    @GetMapping("/agent/setting")
    public Object basicAgentInfo(HttpServletRequest request) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            Agent agentFromRequest = tokenService.getAgentFromRequest(authorizationHeader);
            Agent agent = settingService.getAgent(agentFromRequest.getId());
            return new SettingAgentDTO(agent.getA_name(), agent.getA_nickname(), agent.getA_pwd(), agent.getA_ph());
        } catch (NullPointerException e) {
            throw new NullPointerException("사용자 정보 없음.");
        }
    }

}
