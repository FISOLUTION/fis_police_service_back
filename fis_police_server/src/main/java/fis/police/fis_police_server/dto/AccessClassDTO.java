package fis.police.fis_police_server.dto;

import lombok.Data;

@Data
public class AccessClassDTO {
    private Long class_id;  // 필수
    private Long child_id;  // 학부모가 교실에 접속하려할 때만 필요, 교사인 경우 null
}
