package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;

import java.util.List;

public interface MapService {

    //선택된 시설 중심으로 가까운 agent List 보낸다
    List<Agent> agentNearCenter(Center center);

    //선택된 시설 중심으로 일정거리(distance)의 Center List 보낸다
    List<Center> centerNearCenter(Center center, Long distance);

}
