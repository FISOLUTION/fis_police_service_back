package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Complete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmFromAgentRequest {

//    private Long center_id;
//    private Long agent_id;  // request 에서 얻어오는 방식으로 해야함

//    private String visit_date;
//    private String visit_time;

    private String new_child;
    private String old_child;
    private String senile;
    private String disabled;
    private String etc;

    private Complete complete;
}
