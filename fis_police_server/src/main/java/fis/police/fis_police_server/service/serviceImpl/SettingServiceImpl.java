package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.repository.AgentRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.SettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {

    private final AgentRepository agentRepository;
    private final OfficialsRepository officialsRepository;

    @Override
    public Agent getAgent(Long id) {
        return agentRepository.findById(id);
    }

    @Override
    public Officials getOfficial(Long id) {
        return officialsRepository.findById(id);
    }

}
