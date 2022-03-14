package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.LoginResponse;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.TokenService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final AgentRepository agentRepository;
    private final OfficialsRepository officialsRepository;

    @Override
    public Agent getAgentFromRequest(String authorization) {
        Claims token = parseJwtToken(authorization);
        Long agent_id = Long.valueOf(token.get("id").toString());
        return agentRepository.findById(agent_id);
    }

    @Override
    public Officials getOfficialFromRequest(String authorization) {
        Claims token = parseJwtToken(authorization);
        Long official_id = Long.valueOf(token.get("id").toString());
        return officialsRepository.findById(official_id);
    }

    @Override
    public Claims parseJwtToken(String authorizationHeader) {
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalStateException("NoToken");
        }
        String token = authorizationHeader.substring("Bearer ".length());
        try {
            return Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new JwtException("ExpiredToken");
        }
    }

    @Override
    public String makeToken(Long loginUserId, LoginResponse loginResponse) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(60).toMillis()))

                .claim("id", loginUserId)
                .claim("username", loginResponse.getU_name())
                .claim("role", loginResponse.getU_auth())
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

//    public Authentica
}
