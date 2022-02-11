package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Confirm;
import fis.police.fis_police_server.dto.ConfirmFromAgentRequest;

import java.util.List;

public interface ConfirmService {

    void saveConfirm(ConfirmFromAgentRequest request, Agent agent, Center center);

    Confirm findConfirm();

    List<Confirm> findConfirmList();

}
