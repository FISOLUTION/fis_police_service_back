package fis.police.fis_police_server.dto;

import lombok.Data;

@Data
public class ChildModifyRequest {
    private Long child_id;
    private Long class_id;
    private String name;
    private String birthday;
}
