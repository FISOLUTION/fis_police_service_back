package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingParentDTO {
    private String parent_name;
    private String parent_ph;
    private String parent_email;
    private List<ChildListDTO> children;

    public SettingParentDTO(String name, String ph, String email) {
        this.parent_name = name;
        this.parent_ph = ph;
        this.parent_email = email;
    }
}
