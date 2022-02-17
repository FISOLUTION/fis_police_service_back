package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.LoginResponse;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface TokenService {
    Agent getAgentFromRequest(HttpServletRequest request);
    Officials getOfficialFromRequest(HttpServletRequest request);
    Claims parseJwtToken(HttpServletRequest request);
    String makeToken(Long loginUserId, LoginResponse loginResponse);
}
