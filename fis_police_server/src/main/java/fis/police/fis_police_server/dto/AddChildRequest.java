package fis.police.fis_police_server.dto;

import lombok.Data;

@Data
public class AddChildRequest {
    private String child_name;
    private String child_birth;
    private String child_class;
}
