package fis.police.fis_police_server.dto;

import lombok.Data;

@Data
public class ChildSaveRequest {
    private String child_name;
    private String child_birth;
    private Long class_id;
}
