package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.ParentController;
import fis.police.fis_police_server.dto.ParentSaveRequest;
import fis.police.fis_police_server.service.interfaces.ParentService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class ParentControllerImpl implements ParentController {

    private final ParentService parentService;
    private final TokenService tokenService;

    @Override
    @PostMapping("/parent")
    public void save(@RequestBody ParentSaveRequest request) {
        log.info("[url: {}] [요청: 학부모 회원가입]", "/parent");
        parentService.save(request);
    }

}
