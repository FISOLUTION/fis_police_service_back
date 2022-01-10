package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.UserSaveRequest;
import fis.police.fis_police_server.dto.UserSaveResponse;
import fis.police.fis_police_server.repository.UserRepository;
import fis.police.fis_police_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


/*
    날짜 : 2022/01/10 11:39 오전
    작성자 : 원보라
    작성내용 : userServiceImpl
*/

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //== 콜직원 추가 ==//
    @Override
    @Transactional
    public UserSaveResponse saveUser(UserSaveRequest request) {
        validateDuplicateUser(request); //닉네임 중복 검사
        User user = User.creatUser(request);// 회원 정보 저장
        userRepository.save(user);
        UserSaveResponse userSaveResponse = new UserSaveResponse();
        userSaveResponse.setId(user.getId());
        return userSaveResponse;
    }

    //== 닉네임 중복 검사 ==//
    private void validateDuplicateUser(UserSaveRequest request) {
        List<User> findUser = userRepository.findByNickname(request.getU_nickname());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }

    //== 콜직원 수정 ==//
    @Override
    @Transactional
    public UserSaveResponse modifyUser(UserSaveRequest request){
            //Long id, String u_nickname, String u_name, String u_pwd, String u_ph, LocalDate u_sDate, UserAuthority u_auth) {
        User user = userRepository.findById(request.getUser_id());
        user.updateUser(request.getU_nickname(), request.getU_name(), request.getU_pwd(), request.getU_ph(), request.getU_sDate(), request.getU_auth());
        UserSaveResponse userSaveResponse = new UserSaveResponse();
        userSaveResponse.setId(user.getId());
        return userSaveResponse;
    }

    //== 콜직원 한명 조회 =//
    @Override
    @Transactional
    public User findOneUser(Long id) {
        return userRepository.findById(id);
    }

    //== 콜직원 전체 조회 ==//
    @Override
    @Transactional(readOnly = true)
    public List<User> getUser() {
        return userRepository.findAll();
    }
}
