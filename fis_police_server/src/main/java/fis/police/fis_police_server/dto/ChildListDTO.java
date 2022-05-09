package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Accept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildListDTO {
    private Long child_id;
    private String child_name;
    private String child_birth;
    private CenterNameDTO center;
    private ClassDataDTO aclass;
    private Accept accept;

    public ChildListDTO(Long child_id, String child_name, String child_birth, Accept accept) {
        this.child_id = child_id;
        this.child_name = child_name;
        this.child_birth = child_birth;
        this.accept = accept;
    }
}
