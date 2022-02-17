package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.dto.LoginResponse;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.AppLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppLoginServiceImpl implements AppLoginService {

    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final OfficialsRepository officialsRepository;

    @Override
    public Long loginUserId(LoginRequest request) {
        String nickname = request.getU_nickname();
        String pwd = request.getU_pwd();
        return getPrimaryKey(nickname, pwd);

    }

    @Override
    public LoginResponse loginRes(LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();

        String nickname = request.getU_nickname();
        String pwd = request.getU_pwd();

        List<User> user = userRepository.findByNickname(nickname);
        List<Agent> agent = agentRepository.findByNickname(nickname);
        System.out.println("nickname = " + nickname);
        System.out.println("agent.size() = " + agent.size());
        List<Officials> officials = officialsRepository.findByNickname(nickname);

        if (user.size() + agent.size() + officials.size() != 0) {
            if (user.size() != 0) {
                return authenticateUser(user, loginResponse, pwd);
            } else if (agent.size() != 0) {
                return authenticateAgent(agent, loginResponse, pwd);
            } else {
                return authenticateOfficial(officials, loginResponse, pwd);
            }
        } else {
            loginFail(loginResponse, "idFail");
            return loginResponse;
        }
    }

    @Override
    public LoginResponse loginCheck(Long loginUser) {
        return null;
    }



    private LoginResponse authenticateUser(List<User> user, LoginResponse loginResponse, String pwd) {
        if(!user.get(0).getU_pwd().equals(pwd)) {
            loginFail(loginResponse, "pwdFail");
        } else {
            loginResponse.setSc("success");
            loginResponse.setU_name(user.get(0).getU_name());
            loginResponse.setU_auth(user.get(0).getU_auth());
        }
        return loginResponse;
    }
    private LoginResponse authenticateAgent(List<Agent> agent, LoginResponse loginResponse, String pwd) {
        if(!agent.get(0).getA_pwd().equals(pwd)) {
            loginFail(loginResponse, "pwdFail");
        } else {
            loginResponse.setSc("success");
            loginResponse.setU_name(agent.get(0).getA_name());
            loginResponse.setU_auth(agent.get(0).getU_auth());
        }
        return loginResponse;
    }
    private LoginResponse authenticateOfficial(List<Officials> officials, LoginResponse loginResponse, String pwd) {
        if(!officials.get(0).getO_pwd().equals(pwd)) {
            loginFail(loginResponse, "pwdFail");
        } else {
            loginResponse.setSc("success");
            loginResponse.setU_name(officials.get(0).getO_name());
            loginResponse.setU_auth(officials.get(0).getU_auth());
        }
        return loginResponse;
    }

    private void loginFail(LoginResponse response, String message) {
        response.setSc(message);
        response.setU_name(null);
        response.setU_auth(null);
    }

    private Long getPrimaryKey(String nickname, String pwd) {
        List<User> user = userRepository.findByNickname(nickname);
        List<Agent> agent = agentRepository.findByNickname(nickname);
        List<Officials> officials = officialsRepository.findByNickname(nickname);
        if (user.size() != 0) {
            if (user.get(0).getU_pwd().equals(pwd)) {
                return user.get(0).getId();
            }
        } else if (agent.size() != 0) {
            if (agent.get(0).getA_pwd().equals(pwd)) {
                return agent.get(0).getId();
            }
        } else if (officials.size() != 0){
            if (officials.get(0).getO_pwd().equals(pwd)) {
                return officials.get(0).getId();
            }
        }
        return null;
    }
}
