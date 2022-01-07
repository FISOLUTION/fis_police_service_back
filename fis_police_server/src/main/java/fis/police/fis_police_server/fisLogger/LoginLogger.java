package fis.police.fis_police_server.fisLogger;

import fis.police.fis_police_server.domain.User;
import java.time.LocalDateTime;

public class LoginLogger {
    public void Login(User user){
        System.out.println(LocalDateTime.now() + "에 누가 로그인 했습니다" + user.getU_name());
    }
}
