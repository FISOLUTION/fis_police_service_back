package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.repository.interfaces.CallRepository;
import fis.police.fis_police_server.repository.interfaces.UserRepository;
import fis.police.fis_police_server.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
    private final CallRepository callRepository;

    //== 콜직원 추가 ==//
    @Override
    @Transactional
    public UserSaveResponse saveUser(UserSaveRequest request) {
        validateDuplicateUser(request); //닉네임 중복 검사
        User user = User.creatUser(request);// 회원 정보 저장
        userRepository.save(user);
        UserSaveResponse userSaveResponse = new UserSaveResponse();
        userSaveResponse.setUser_id(user.getId());
        return userSaveResponse;
    }

    //== 닉네임 중복 검사 ==//
    private void validateDuplicateUser(UserSaveRequest request) {
        List<User> findUser = userRepository.findByNickname(request.getU_nickname());
        if (findUser.size() > 0) { //수정 시 중복 검사
            if (!findUser.get(0).getId().equals(request.getUser_id())) { //자신의 이름은 중복검사 안함
                throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
            }
        }
    }

    //== 콜직원 수정 ==//
    @Override
    @Transactional
    public UserSaveResponse modifyUser(UserSaveRequest request) {
        User user = userRepository.findById(request.getUser_id());
        validateDuplicateUser(request); //닉네임 중복 검사
        user.updateUser(request.getU_nickname(), request.getU_name(), request.getU_pwd(), request.getU_ph(), request.getU_sDate(), request.getU_auth());
        UserSaveResponse userSaveResponse = new UserSaveResponse();
        userSaveResponse.setUser_id(user.getId());
        return userSaveResponse;
    }

    //== 콜직원 한명 조회 =//
    @Override
    @Transactional
    public User findOneUser(Long id) {
        try {
            return userRepository.findById(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("담당 콜직원 정보 존재하지 않음.");
        }
    }

    //== 콜직원 전체 조회 ==//
    @Override
    @Transactional(readOnly = true)
    public List<UserInfoResponse> getUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserInfoResponse(user.getId(), user.getU_nickname(), user.getU_name(), user.getU_pwd(), user.getU_ph(), user.getU_sDate(), user.getU_auth(),0,0))
                .collect(Collectors.toList());
    }

    //== user 별 오늘 통화 건수 ==//
    @Override
    @Transactional
    public List<CallTodayDTO> todayCallNum(String today) {
        List<CallTodayDTO> CallTodayDTOList = callRepository.todayCallNum(today);
        return CallTodayDTOList;
    }


    //== user 별 총 통화 건수 ==//
    @Override
    @Transactional
    public List<CallAvgDTO> totalCallNum() {
        List<CallAvgDTO> CallTotalDTOList = callRepository.totalCallNum();
        return CallTotalDTOList;
    }

    @Override
    public List<CallHistoryResponse> findUserAndCallByDate(String date) {
        List<CallHistoryResponse> response = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            addCallHistory(date, response, user);
        }
        return response;
    }

    private void addCallHistory(String date, List<CallHistoryResponse> response, User user) {
        List<Call> calls = userRepository.findUserAndCallByDate(date, user.getId());
        int total = calls.size();
        int p = 0;
        int r = 0;
        int h = 0;
        int n = 0;
        for (Call call : calls) {
            switch (call.getParticipation()) {
                case PARTICIPATION:
                    p++;
                    break;
                case REJECT:
                    r++;
                    break;
                case HOLD:
                    h++;
                    break;
                case NONE:
                    n++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + call.getParticipation());
            }
        }
        response.add(new CallHistoryResponse(user.getId(), user.getU_nickname(), user.getU_name(),
                user.getU_auth(), total, p, r, h, n));
    }

    @Override
    public List<CallHistoryResponse> findUserAndCallByDateOptimize(String date) {
        List<CallHistoryResponse> callRecordsResponse = userRepository.findUsers();
        List<Call> calls = userRepository.findWithCalls(date);
        Map<Long, List<Call>> callMap = calls.stream()
                .collect(Collectors.groupingBy(c -> c.getUser().getId()));
        callRecordsResponse.forEach(u -> {
            List<Call> callsByUser = callMap.getOrDefault(u.getUser_id(), new ArrayList<>());
            if (callsByUser.isEmpty()) {
                return;
            }
            int t = callsByUser.size();
            addCallRecords(u, callsByUser, t);
        });
        return callRecordsResponse;
    }

    private void addCallRecords(CallHistoryResponse u, List<Call> callsByUser, int t) {
        int p = 0, r = 0, h = 0, n = 0;
        for (Call call : callsByUser) {
            switch (call.getParticipation()) {
                case PARTICIPATION:
                    p++;
                    break;
                case REJECT:
                    r++;
                    break;
                case HOLD:
                    h++;
                    break;
                case NONE:
                    n++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + call.getParticipation());
            }
        }
        u.updateCallRecords(t, p, r, h, n);
    }

}
