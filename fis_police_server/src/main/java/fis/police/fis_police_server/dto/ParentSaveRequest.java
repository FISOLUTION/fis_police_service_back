package fis.police.fis_police_server.dto;

import lombok.Data;

@Data
public class ParentSaveRequest {

    private String nickname;
    private String pwd;

    private String name;
    private String ph;
    private String email;
}
