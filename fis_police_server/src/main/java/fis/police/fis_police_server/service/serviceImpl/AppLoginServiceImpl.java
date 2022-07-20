package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.*;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.interfaces.AgentRepository;
import fis.police.fis_police_server.repository.interfaces.OfficialsRepository;
import fis.police.fis_police_server.repository.interfaces.ParentRepository;
import fis.police.fis_police_server.service.interfaces.AppLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
    작성 날짜: 2022/03/23 2:38 오후
    작성자: 고준영
    작성 내용: 앱 로그인
*/
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AppLoginServiceImpl implements AppLoginService {

    private final AgentRepository agentRepository;
    private final OfficialsRepository officialsRepository;
    private final ParentRepository parentRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Long getPrimaryKey(AppLoginRequest request) {
        if (request.getRole() == UserAuthority.AGENT) {
            List<Agent> agent = agentRepository.findByNickname(request.getU_nickname());
            if (agent.size() != 0) {
                if (agent.get(0).getA_pwd().equals(request.getU_pwd())) {
                    return agent.get(0).getId();
                }
            }
        } else if (request.getRole() == UserAuthority.DIRECTOR || request.getRole() == UserAuthority.TEACHER) {
            List<Officials> officials = officialsRepository.findByNickname(request.getU_nickname());
            if (officials.size() != 0) {
                if (encoder.matches(request.getU_pwd(), officials.get(0).getO_pwd())) {
                    return officials.get(0).getId();
                }
            }
        } else if (request.getRole() == UserAuthority.CHILD || request.getRole() == UserAuthority.PARENT){
            List<Parent> parent = parentRepository.findByNickname(request.getU_nickname());
            if (!parent.isEmpty()) {
                if (parent.get(0).getP_pwd().equals(request.getU_pwd())) {
                    return parent.get(0).getId();
                }
            }

        } else {
            throw new IllegalArgumentException("role 정보 오류");
        }
        return null;
    }

    @Override
    public LoginResponse login(AppLoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();

        String nickname = request.getU_nickname();
        String pwd = request.getU_pwd();
        UserAuthority role = request.getRole();

        if (role == UserAuthority.AGENT) {
            log.info("[로그인 요청 역할 {}]", role);
            List<Agent> agent = agentRepository.findByNickname(nickname);
            if (!agent.isEmpty()) {
                return authenticateAgent(agent, loginResponse, pwd);
            } else {
                loginFail(loginResponse, "idFail");
                return loginResponse;
            }
        } else if (role == UserAuthority.DIRECTOR || role == UserAuthority.TEACHER) {
            log.info("[로그인 요청 역할 {}]", role);
            List<Officials> officials = officialsRepository.findByNickname(nickname);
            if (!officials.isEmpty()) {
                return authenticateOfficial(officials, loginResponse, pwd);
            } else {
                loginFail(loginResponse, "idFail");
                return loginResponse;
            }
        } else if (role == UserAuthority.PARENT) {
            log.info("[로그인 요청 역할 {}]", role);
            List<Parent> parent = parentRepository.findByNickname(nickname);
            if (!parent.isEmpty()) {
                return authenticateParent(parent, loginResponse, pwd);
            } else {
                loginFail(loginResponse, "idFail");
                return loginResponse;
            }
        } else {
            throw new IllegalArgumentException("role 정보 오류");
        }
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
//        if(!officials.get(0).getO_pwd().equals(pwd)) {
        if(!encoder.matches(pwd, officials.get(0).getO_pwd())) {
            loginFail(loginResponse, "pwdFail");
        } else {
            loginResponse.setSc("success");
            loginResponse.setU_name(officials.get(0).getO_name());
            loginResponse.setU_auth(officials.get(0).getU_auth());
            Center center = officials.get(0).getCenter();
            loginResponse.setCenter(
                    new CenterDataResponse(center.getId(), center.getC_name(), center.getC_address(),
                            center.getC_zipcode(), center.getC_ph(), null));
            List<Aclass> aclassList = center.getAclassList();
            List<ClassDataDTO> classes = aclassList.stream()
                    .map(aclass -> new ClassDataDTO(aclass.getId(), aclass.getName()))
                    .collect(Collectors.toList());
            loginResponse.setCenter(new CenterDataResponse(center.getId(), center.getC_name(), center.getC_address(), center.getC_zipcode(), center.getC_ph(), classes, null));
        }
        return loginResponse;
    }
    private LoginResponse authenticateParent(List<Parent> parents, LoginResponse loginResponse, String pwd) {
        if (!parents.get(0).getP_pwd().equals(pwd)) {
            loginFail(loginResponse, "pwdFail");
        } else {
            loginResponse.setSc("success");
            List<Child> childList = parents.get(0).getChildList();
            List<ChildListDTO> children = childList.stream()
                            .map(child -> new ChildListDTO(child.getId(), child.getName(), child.getBirthday(),
                                    new CenterNameDTO(child.getAclass().getCenter().getId(), child.getAclass().getCenter().getC_name()),
                                    new ClassDataDTO(child.getAclass().getId(), child.getAclass().getName()),
                                    child.getAccept()))
                                    .collect(Collectors.toList());
            loginResponse.setChildren(children);
            loginResponse.setU_name(parents.get(0).getName());
            loginResponse.setU_auth(parents.get(0).getU_auth());
        }
        return loginResponse;
    }

    private void loginFail(LoginResponse response, String message) {
        response.setSc(message);
        response.setU_name(null);
        response.setU_auth(null);
    }

}
