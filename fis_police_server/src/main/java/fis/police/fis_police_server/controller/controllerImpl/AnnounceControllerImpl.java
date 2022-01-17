package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AnnounceController;
import fis.police.fis_police_server.service.serviceImpl.MapConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AnnounceControllerImpl implements AnnounceController {

    private final MapConfig mapConfig;

    @Override
    public Boolean announceSchedule() {
        return null;
    }

    @Override
    @CrossOrigin
    @GetMapping("/kakao/authlink") // 카카오 인증 페이지 링크 주기
    public String authLink() {
        return "https://kauth.kakao.com/oauth/authorize?response_type=code&" +
                "client_id=" + mapConfig.getKakaoApiId() + "&" +
                "redirect_uri=" + mapConfig.getKakaoRedirectUri();
    }

    @Override
    @CrossOrigin
    @GetMapping("/kakao/authcode") // 카카오 인증코드 받아와서 액세스 토큰을 기반으로 한 세션 attribute 추가
    public void authCode(@RequestParam("code") String code) {

    }


}
