package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.LoginController;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.LoginRequest;
import fis.police.fis_police_server.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

    private final LoginService loginService;

    @Override
    @CrossOrigin
    @PostMapping("/login")
    //나중에 url 정해지면 다시 보기
    public String login(@RequestBody LoginRequest loginrequest, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        Long loginUserId = loginService.login(loginrequest);

        //로그인 실패
        if (loginUserId == null) {
            return "fail";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession(); //디폴트 True: 기존있으면 기존반환, 없을 때 새로 생성  <-> false: 없을 때 새로 생성안함
        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginUser", loginUserId);
        return "success";
    }


    @Override
    @CrossOrigin
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션 지움
        }
        return "logout";
    }

    @Override
    @CrossOrigin
    @GetMapping("/")
    //이미 로그인 된 사용자를 찾을 때 (이 기능은 세션을 생성하지 않음)
    public String loginSuccess(@SessionAttribute(name = "loginUser", required = false) Long loginUser, Model model) {
        if (loginUser == null) {
            return "returnToLogin";
        }
        //세션이 유지되면
        model.addAttribute("loginUser", loginUser);
        return "LoginSuccess";
    }
}



