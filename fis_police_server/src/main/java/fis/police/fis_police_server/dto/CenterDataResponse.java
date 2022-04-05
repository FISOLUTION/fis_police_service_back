package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.Aclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterDataResponse {

    private Long center_id;
    private String c_name;
    private String c_address;
    private String c_zipcode;
    private String c_ph;
    private List<ClassDataDTO> classes;
    private List<OfficialDTO> officials;

    public CenterDataResponse(Long id, String c_name, String c_address, String c_zipcode, String c_ph, List<ClassDataDTO> classes) {
        this.center_id = id;
        this.c_name = c_name;
        this.c_address = c_address;
        this.c_zipcode = c_zipcode;
        this.c_ph = c_ph;
        this.classes = classes;
    }
}
