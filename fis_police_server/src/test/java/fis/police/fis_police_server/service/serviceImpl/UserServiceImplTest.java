package fis.police.fis_police_server.service.serviceImpl;

import com.sun.net.httpserver.Authenticator;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.UserSaveRequest;
import fis.police.fis_police_server.dto.UserSaveResponse;
import fis.police.fis_police_server.repository.CallRepository;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.UserService;
import org.assertj.core.api.Assertions;
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
    CallRepository callRepository;
    @Autowired
    UserService userService;

    @Test
    public void 신규_회원가입() {
        //given
        final UserSaveRequest saveDto1 = new UserSaveRequest(null, "test001", "원보라", "1234", "010-0000-0001", LocalDate.of(2021, 02, 04), UserAuthority.ADMIN);

        //when
        UserSaveResponse res = userService.saveUser(saveDto1);

        //then
        org.junit.jupiter.api.Assertions.assertEquals(res.getUser_id(), userService.findOneUser(res.getUser_id()).getId());
    }

    @Test
    public void 기존_회원수정() {
        //given
        final UserSaveRequest saveDto1 = new UserSaveRequest(null,"test001", "원보라", "1234", "010-0000-0001", LocalDate.of(2021, 02, 04), UserAuthority.ADMIN);
        UserSaveResponse res = userService.saveUser(saveDto1);
        final UserSaveRequest changeUser = new UserSaveRequest(res.getUser_id(),"test002", "2", "2", "010-0000-0002", LocalDate.of(2021, 02, 04), UserAuthority.ADMIN);

        //when
        userService.modifyUser(changeUser);

        //then
        Assertions.assertThat(changeUser.getU_nickname()).isEqualTo((userService.findOneUser(res.getUser_id()).getU_nickname()));
    }


    @Test
    public void 회원가입_닉네임_중복() {
        //given
        final UserSaveRequest saveDto1 = new UserSaveRequest(null, "test001", "원보라", "1234", "010-0000-0001", LocalDate.of(2021, 02, 04), UserAuthority.ADMIN);
        final UserSaveRequest saveDto2 = new UserSaveRequest(null, "test001", "원보라2", "1234", "010-0000-0002", LocalDate.of(2021, 02, 04), UserAuthority.USER);

        //when
        userService.saveUser(saveDto1);

        //then
        try {
            userService.saveUser(saveDto2);    // 예외 발생
            fail();
        } catch (IllegalStateException e) {
            System.out.println("닉네임 중복============================");
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 닉네임 입니다.");
        }
    }


    @Test
    public void 한명_조회() {

    }

    @Test
    public void 전체_조회() {


    }

    @Test
    public void 사용자_오늘_통화건수() {


    }

    @Test
    public void 사용자_총_통화건수() {
        //평균아니고 총 통화건수임


    }


}