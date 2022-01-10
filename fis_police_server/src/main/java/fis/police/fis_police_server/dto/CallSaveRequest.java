package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.InOut;
import fis.police.fis_police_server.domain.enumType.Participation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallSaveRequest {

//    private Long id;
    private Center center;  // 추후에 center_id만 받아와서 center을 찾는 로직 구현해야 할 것으로 예상됨
    private User user;      // 위와 마찬가지
    private LocalDateTime dateTime;
    private Participation participation;
    private InOut in_out;
    private String c_manager;
    private String m_ph;
    private String m_email;
    private Integer num;
    private String center_etc;
    private String agent_etc;

}
