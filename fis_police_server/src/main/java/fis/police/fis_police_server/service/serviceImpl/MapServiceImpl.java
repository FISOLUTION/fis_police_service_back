package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.service.MapService;

import java.util.List;

public class MapServiceImpl implements MapService {


    @Override
    public List<Agent> agentNearCenter(Center center) {
        return null;
    }

    @Override
    public List<Center> centerNearCenter(Center center, Long distance) {
        return null;
    }
}
