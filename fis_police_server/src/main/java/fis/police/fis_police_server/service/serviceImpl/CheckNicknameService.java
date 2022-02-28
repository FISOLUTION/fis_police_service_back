package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckNicknameService {

    private final AgentRepository agentRepository;
    private final UserRepository userRepository;
    private final OfficialsRepository officialsRepository;

    public void CheckNicknameOverlap(String nickname) {
        List<Agent> agentList = agentRepository.findByNickname(nickname);
        List<User> userList = userRepository.findByNickname(nickname);
        List<Officials> officialsList = officialsRepository.findByNickname(nickname);

        if (agentList.size() > 0) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        } else if (userList.size() > 0) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        } else if (officialsList.size() > 0) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }
}
