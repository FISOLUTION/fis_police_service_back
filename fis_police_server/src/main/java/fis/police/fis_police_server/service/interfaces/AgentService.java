package fis.police.fis_police_server.service.interfaces;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.dto.*;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.AddressException;
import java.util.List;

public interface AgentService {
    // 현장요원 추기
    Agent saveAgent(AgentSaveRequest agentSaveResponse) throws ParseException,
            RestClientException, IllegalStateException, IndexOutOfBoundsException, AddressException;

    // 현장요원 수정
    Agent modifyAgent(AgentModifyRequest agentModifyRequest) throws ParseException, IllegalStateException, AddressException;

    Agent findById(Long id);

    // 현장요원 조회
    List<Agent> getAgents();


    /*
        날짜 : 2022/02/15 1:36 오후
        작성자 : 원보라
        작성내용 : 현장요원 사진 추가
    */
    void updatePicture(Long agent_id, MultipartFile multipartFile);

    String getPicture(Long agent_id);

    void deletePicture(Long agent_id);

    void saveCurrentLocation(Long agent_id, AgentLocation agentLocation);

    List<AgentByMonthDTO> searchByMonth(String month, String keyword);
}
