package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.enumType.Accept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildListDTO {
    private String child_name;
    private String child_birth;
    private String center_name;
    private String class_name;
    private Accept accept;
}
