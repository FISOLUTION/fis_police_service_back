package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.interfaces.CheckController;
import fis.police.fis_police_server.dto.CheckRequest;
import fis.police.fis_police_server.service.interfaces.CheckService;
import fis.police.fis_police_server.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *    날짜 : 2022/03/31 4:13 오후
 *    작성자 : 원보라
 *    작성내용 : child 알림장 확인 기능
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class CheckControllerImpl implements CheckController {
    private final CheckService checkService;
    private final TokenService tokenService;

    @Override
    @PostMapping("/check")
    public void SaveCheck(@RequestBody CheckRequest checkRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        try {
            checkService.saveCheck(checkRequest);            //성공시 200 ok
            log.info("check 완료");
        } catch (IllegalCallerException e) {
            throw new IllegalCallerException("AlreadyCheck");
        } catch (IllegalStateException e) {
            throw new IllegalStateException("NoToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("NoCheck");
        }
    }
}
