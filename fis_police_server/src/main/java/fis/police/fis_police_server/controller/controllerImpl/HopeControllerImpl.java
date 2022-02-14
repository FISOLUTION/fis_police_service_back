package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.HopeController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.enumType.Complete;
import fis.police.fis_police_server.dto.HopeSaveRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.repository.HopeRepository;
import fis.police.fis_police_server.service.HopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/*
    작성 날짜: 2022/02/14 11:33 오전
    작성자: 고준영
    작성 내용: 신청서 작성(제출), 신청서 조회
*/
@RestController
@RequiredArgsConstructor
public class HopeControllerImpl implements HopeController {

    private final HopeService hopeService;
    private final HopeRepository hopeRepository;

    @Override
    @PostMapping("/hope")
    public void saveHope(HttpServletRequest request, @RequestBody HopeSaveRequest hopeRequest) {
        Officials officials = hopeService.findOfficials(hopeRequest.getO_id());
        Center center = hopeService.findCenter(officials.getCenter().getId());
        hopeService.saveHope(hopeRequest, center, officials);
    }

    @Override
    @GetMapping("/hope")
    public Result getHope() {
        return hopeService.listHope();
    }

    @Override
    @PostMapping("/hope/{hope_id}")
    public void updateComplete(@PathVariable Long hope_id) {
        hopeRepository.updateHopeComplete(hope_id, Complete.complete);
    }
}
