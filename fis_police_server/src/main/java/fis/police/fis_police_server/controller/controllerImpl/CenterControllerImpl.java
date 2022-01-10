package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CenterController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.CenterModifyDTO;
import fis.police.fis_police_server.dto.CenterSaveDTO;
import fis.police.fis_police_server.dto.SearchCenterDTO;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class CenterControllerImpl implements CenterController {

    private final CenterService centerService;
    /*
        날짜 : 2022/01/10 3:11 오후
        작성자 : 현승구
        작성내용 :
    */
    @PostMapping
    @Override
    public List<SearchCenterResponseDTO> searchCenter(@RequestParam String c_name, @RequestParam String c_address, @RequestParam String c_ph) {
        SearchCenterDTO searchCenterDTO = new SearchCenterDTO(c_name, c_address, c_ph);
        List<SearchCenterResponseDTO> SearchCenterResponseDTO = centerService.findCenterList(searchCenterDTO);
        return SearchCenterResponseDTO;
    }

    @Override
    public Object selectCenter(@RequestParam Long center_id) {
        return null;
    }

    @Override
    public Object selectDate(@RequestParam Long center_id, @RequestParam String date) {
        return null;
    }

    @Override
    public Object searchNearCenter(@RequestParam Long center_id, @RequestParam Long range) {
        return null;
    }

    @Override
    public Boolean saveCenter(@RequestBody CenterSaveDTO centerSaveDTO) {
        return null;
    }

    @Override
    public Boolean modifyCenter(@RequestBody CenterModifyDTO centerModifyDTO) {
        return null;
    }

    @Override
    public List<Object> getCenter(@RequestParam Long center) {
        return null;
    }

}
