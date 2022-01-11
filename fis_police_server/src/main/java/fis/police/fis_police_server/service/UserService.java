package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.UserSaveRequest;
import fis.police.fis_police_server.dto.UserSaveResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public interface UserService {
    // 콜직원 추가
     UserSaveResponse saveUser(UserSaveRequest request);

    // 콜직원 수정
    UserSaveResponse modifyUser(UserSaveRequest request);
            //Long id, String nickname, String name, String pwd, String ph, LocalDate sDate, UserAuthority auth);

    // 콜직원 한명 조회
    User findOneUser(Long id);

    // 콜직원 전체 조회
    List<User> getUser();
}
