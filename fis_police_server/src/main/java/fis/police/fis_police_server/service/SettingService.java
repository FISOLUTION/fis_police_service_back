package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.User;

public interface SettingService {
    Agent getAgent(Long id);
    Officials getOfficial(Long id);
}
