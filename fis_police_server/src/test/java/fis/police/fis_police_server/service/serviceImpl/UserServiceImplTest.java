package fis.police.fis_police_server.service.serviceImpl;

import com.sun.net.httpserver.Authenticator;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.UserSaveRequest;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;



    @Test
    public void 회원가입_닉네임_중복() throws Exception {
        //given
//        final UserSaveRequest saveDto1 = new UserSaveRequest(1L, "fis001", "원보라", "1234", "010-0000-0001", LocalDate.of(2021,01,01), UserAuthority.ADMIN);
//        final UserSaveRequest saveDto2 = new UserSaveRequest(2L, "fis001", "원보라2", "1234", "010-0000-0002", LocalDate.of(2021,01,02), UserAuthority.USER);
//
//        //when
//        userService.saveUser(saveDto1);
//        try {
//            userService.saveUser(saveDto2);    // 예외 발생
//        } catch (IllegalStateException e) {
//            System.out.println("예외발생============================");
//        }
//        //then
//        System.out.println(saveDto1.getU_nickname());
//        System.out.println(saveDto2.getU_nickname());
    }
}