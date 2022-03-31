package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.Aclass;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CenterDataResponse {

    private Long center_id;
    private String c_name;
    private String c_address;
    private String c_zipcode;
    private String c_ph;
    private List<ClassDataDTO> classes;
}
