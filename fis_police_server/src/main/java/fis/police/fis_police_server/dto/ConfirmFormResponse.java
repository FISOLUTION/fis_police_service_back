package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Complete;
import lombok.Data;

import java.util.List;

@Data
public class ConfirmFormResponse {

    private String center_name;
    private String center_address;
    private String center_ph;   // 시설 담당자 전화번호 혹은 시설 전화번호
    private String visit_date;  // schedule 에서 뽑아와야함
    private String visit_time;  // schedule 에서 뽑아와야함
    private String new_child;
    private String old_child;
    private String senile;
    private String disabled;
    private String etc;

    private List<String> agent_name;

    private Complete complete;  // 결재 여부
//    private String center_manager;  // 결재 당시 시설 담당자
}
