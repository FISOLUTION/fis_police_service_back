package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CallController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.CallSaveRequest;
import fis.police.fis_police_server.dto.CallSaveResponse;
import fis.police.fis_police_server.service.UserService;
import fis.police.fis_police_server.service.serviceImpl.CallServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
    작성 날짜: 2022/01/10 1:13 오후
    작성자: 고준영
    작성 내용: call controller 기본 구상
*/
@RestController
@RequiredArgsConstructor
@Slf4j
public class CallControllerImpl implements CallController {

    private final CallServiceImpl callService;
    private final UserService userService;

    @Override
    @PostMapping("/call")
    public CallSaveResponse saveCall(@RequestBody CallSaveRequest request, HttpServletRequest req) {

        // dateTime = 날짜와 시간이므로 date 부분과 time 부분을 자름 (날짜별 통화건수 등을 알아내기 위해 날짜와 시간을 분리함)
        String dateTime = request.getDateTime();
        String date;
        String time;
        try {
            date = dateTime.substring(0, 10);
            time = dateTime.substring(11);
        } catch (NullPointerException e) {
            throw new NullPointerException("시간 정보가 없음.");
        }

        try{
            // 기관과 콜 직원을 찾는 과정에서 NullPointerException 발생 가능
            Center center = callService.findCenter(request);
            HttpSession session = req.getSession();
            Long userId = (Long) session.getAttribute("loginUser");
            User user = userService.findOneUser(userId);
            log.info("[로그인 id값: {}] [url: {}] [요청: 콜기록 저장]", req.getSession().getAttribute("loginUser"), "/call");
            return callService.saveCall(request, center, user, date, time);
        } catch (NullPointerException e) {
            throw new NullPointerException("사용자 혹은 시설 존재하지 않음.");
        }
    }

}
