package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.OfficialsController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.OfficialSaveRequest;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.service.OfficialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    작성 날짜: 2022/02/14 11:35 오전
    작성자: 고준영
    작성 내용: 시설 담당자 회원가입
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class OfficialsControllerImpl implements OfficialsController {

    private final OfficialService officialService;
    private final CenterRepository centerRepository;

    @Override
    @PostMapping("/officials")
    public void saveOfficials(@RequestBody OfficialSaveRequest request) {

        System.out.println("==========================================");
        System.out.println("request.getCenter_id() = " + request.getCenter_id());
        System.out.println("==========================================");
        System.out.println("request = " + request);
        System.out.println("==========================================");



        Center center = officialService.findCenter(request.getCenter_id());
        officialService.saveOfficials(request, center);

    }
}
