package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void 회원가입() throws Exception {
        User user0 = new User();
        user0.setU_nickname("fis000");

        userService.saveUser(user0);
    }


    @Test
    public void 회원가입_닉네임_중복() throws Exception {
        //given
        User user = new User();
        user.setU_nickname("fis001");


        User user1 = new User();
        user1.setU_nickname("fis001");

        //when
        userService.saveUser(user);
        try {
            userService.saveUser(user1);    // 예외 발생
        } catch (IllegalStateException e) {
            return;
        }
        //then
        fail("예외가 발생해야 한다.");
    }
}