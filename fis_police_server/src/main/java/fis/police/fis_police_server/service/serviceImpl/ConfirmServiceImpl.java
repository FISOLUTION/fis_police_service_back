package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Confirm;
import fis.police.fis_police_server.dto.ConfirmFromAgentRequest;
import fis.police.fis_police_server.repository.ConfirmRepository;
import fis.police.fis_police_server.repository.ScheduleRepository;
import fis.police.fis_police_server.service.ConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmServiceImpl implements ConfirmService {

    private final ConfirmRepository confirmRepository;
//    private final ScheduleRepository scheduleRepository;

    @Override
    public void saveConfirm(ConfirmFromAgentRequest request, Agent agent, Center center) {
        Confirm confirm = Confirm.createConfirm(request, agent, center);
        confirmRepository.save(confirm);
    }

    @Override
    public Confirm findConfirm() {
        return null;
    }

    @Override
    public List<Confirm> findConfirmList() {
        return null;
    }



}
