package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.dto.CenterSearchDTO;
import fis.police.fis_police_server.dto.CenterSearchResponseDTO;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.service.CenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AppCenterController {

    private final CenterService centerService;

    @GetMapping("app/center/search")
    public Result searchCenter(@RequestParam @Nullable String c_name, @RequestParam @Nullable String c_address, @RequestParam @Nullable String c_ph, HttpServletRequest request){
        try {
            CenterSearchDTO centerSearchDTO = new CenterSearchDTO(c_name, c_address, c_ph);
            String name = centerSearchDTO.getC_name();
            String address = centerSearchDTO.getC_address();
            String ph = centerSearchDTO.getC_ph();
            List<CenterSearchResponseDTO> results = centerService.findCenterList(name, address, ph);
//            logging(request, "/center/search", "", true);
            return new Result(results);
        } catch (NoResultException noResultException){
//            logging(request, "/center/search", noResultException.getMessage(), false);
            return new Result(new ArrayList<CenterSearchResponseDTO>());
        } catch (NullPointerException nullPointerException){
//            logging(request, "/center/search", "null pointer exception 발생", false);
            return null;
        }
    }
}
