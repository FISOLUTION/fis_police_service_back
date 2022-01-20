package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AnnounceController;
import fis.police.fis_police_server.service.AnnounceService;
import fis.police.fis_police_server.service.serviceImpl.MapConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
/*
    작성날짜: 2022/01/18 11:20 AM
    작성자: 이승범
    작성내용: 카카오 메세지를 통한 일정공지를 위한 컨트롤러 구현
*/
@RestController
@RequiredArgsConstructor
public class AnnounceControllerImpl implements AnnounceController {

    private final MapConfig mapConfig;
    private final AnnounceService announceService;

    @Override
    public Boolean announceSchedule() {
//        announceService.announceSchedule();
        return null;
    }

    @Override
    @CrossOrigin
    @GetMapping("/kakao/authlink") // 카카오 인증 페이지 링크 주기
    public String authLink() {
        return "https://kauth.kakao.com/oauth/authorize?response_type=code&" +
                "client_id=" + mapConfig.getKakaoApiKey() + "&" +
                "redirect_uri=" + mapConfig.getKakaoRedirectUri();
    }

    @Override
    @CrossOrigin
    @GetMapping("/kakao/authcode") // 카카오 인증코드 받아와서 액세스 토큰을 기반으로 한 세션 attribute 추가
    public void authCode(@RequestParam("code") String code) {
        try {
            String accessToken = announceService.getAccessToken(code);
            announceService.announceSchedule(accessToken);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
