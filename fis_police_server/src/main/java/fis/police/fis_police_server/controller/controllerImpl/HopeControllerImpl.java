package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.HopeController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.enumType.Complete;
import fis.police.fis_police_server.dto.HopeSaveRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.repository.HopeRepository;
import fis.police.fis_police_server.service.HopeService;
import fis.police.fis_police_server.service.TokenService;
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
    private final TokenService tokenService;

    @Override
    @PostMapping("/app/hope")
    public void saveHope(HttpServletRequest request, @RequestBody HopeSaveRequest hopeRequest) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            Officials officialFromRequest = tokenService.getOfficialFromRequest(authorizationHeader);
            Center center = hopeService.findCenter(officialFromRequest.getCenter().getId());
            hopeService.saveHope(hopeRequest, center, officialFromRequest);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("시설 담당자 정보 없음.");
        } catch (NullPointerException e) {
            throw new NullPointerException("담당 시설 정보 없음.");
        }
    }

    @Override
    @GetMapping("/hope")
    public Result getHope() {
        return hopeService.listHope();
    }

    @Override
    @PostMapping("/hope/{hope_id}")
    public void updateComplete(@PathVariable Long hope_id) {
        hopeService.updateHopeComplete(hope_id);
    }
}
