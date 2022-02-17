package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.LoginResponse;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    public Agent getAgentFromRequest(HttpServletRequest request) {
        Claims token = parseJwtToken(request);
        System.out.println("token.get(\"role\") = " + token.get("role"));
        System.out.println("token.get(\"id\").getClass() = " + token.get("id").getClass());
        System.out.println("token.get(\"id\").toString() = " + token.get("id").toString());
        Long agent_id = Long.valueOf(token.get("id").toString());
        Agent agent = agentRepository.findById(agent_id);
        return agent;
    }

    @Override
    public Officials getOfficialFromRequest(HttpServletRequest request) {
        Claims token = parseJwtToken(request);
        Long official_id = Long.valueOf(token.get("id").toString());
        Officials official = officialsRepository.findById(official_id);
        return official;
    }

    @Override
    public Claims parseJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalStateException();
        }
        String token = authorizationHeader.substring("Bearer ".length());
        Claims secret = Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody();
        return secret;
    }

    @Override
    public String makeToken(Long loginUserId, LoginResponse loginResponse) {
        Date now = new Date();
        String compact = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(5).toMillis()))

                .claim("id", loginUserId)
                .claim("role", loginResponse.getU_auth())
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
        return compact;
    }
}
