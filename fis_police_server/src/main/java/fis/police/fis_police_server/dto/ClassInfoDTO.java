package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfoDTO {

    private Long class_id;
    private String class_name;
    private List<ChildListDTO> childList;
//    private List<BoardListDTO> boardList;
//    private List<AnnounceListDTO> announceList;
}
