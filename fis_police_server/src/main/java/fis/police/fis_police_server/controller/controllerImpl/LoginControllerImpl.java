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
    @PostMapping("/logintest")
    public String login(@RequestBody LoginRequest loginrequest, HttpServletRequest request) {
        Long loginUserId = loginService.login(loginrequest);

        //로그인 실패
        if (loginUserId == null) {
            return "로그인 실패";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession(); //디폴트 True: 기존있으면 기존반환, 없을 때 새로 생성  <-> false: 없을 때 새로 생성안함
        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginUser", loginUserId);
        return "SuccessLogin";
    }

    @Override
    @CrossOrigin
    @PostMapping("/logouttest")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션 지움
        }
        return "redirect:/";
    }

    @Override
    @CrossOrigin
    @GetMapping("/test")
    //이미 로그인 된 사용자를 찾을 때 (이 기능은 세션을 생성하지 않음)
    public String loginSuccess(@SessionAttribute(name = "loginUser", required = false) Long loginUser, Model model) {
        if (loginUser == null) {
            return "다시 로그인 화면으로 돌아가게 해야함";
        }
        //세션이 유지되면
        model.addAttribute("loginUser", loginUser);
        return "로그인 성공";
    }
}

//////////////////////////////////혹시 V4 실패하면 V3 써야지//////////////////////////////////////
//    @CrossOrigin
//    @PostMapping("/logintest")
//    public String loginV3(@RequestBody LoginRequest loginrequest, HttpServletRequest request) {
//        User loginUser = loginService.login(loginrequest);
//        //로그인 성공 처리
//        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
//        HttpSession session = request.getSession(); //디폴트 True: 기존있으면 기존반환, 없을 때 새로 생성  <-> false: 없을 때 새로 생성안함
//        //세션에 로그인 회원 정보 보관
//        session.setAttribute("loginUser", loginUser);
//        return "loginloginloginlogin";
//    }
//
//    @CrossOrigin
//    @PostMapping("/logouttest")
//    public String logoutV3(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate(); //지움
//        }
//        return "redirect:/";
//    }
//
//    @CrossOrigin
//    @GetMapping("/test")
//    public String loginSuccessV3(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false); //이미 로그인 한 상태이므로 새로운 세션 생성할 필요 없음
//        if (session == null) {
//            return "다시 로그인";
//        }
//       //저장된 사용자 정보 조회
//        User loginUser = (User) session.getAttribute("loginUser");
//        //세션에 데이터가 없으면
//        if (loginUser == null) {
//            return "다시 로그인 해야되고";
//        }
//        //세션이 유지되면
//        model.addAttribute("user", loginUser);
//        return "로그인 성공";
//    }


