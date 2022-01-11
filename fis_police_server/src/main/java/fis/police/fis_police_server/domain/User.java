package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.UserSaveRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter

/*
    날짜 : 2022/01/10 1:16 오후
    작성자 : 원보라
    작성내용 : TEST를 위헤 SETTER 열어둠
*/
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    //user_id         BIGINT              // 'primary_key',

    @Column(length = 100)
    private String u_nickname;              // "사용자 id"

    @Column(length = 10)
    private String u_name;                  // '사용자 이름',

    @Column(length = 100)
    private String u_pwd;                   // '사용자 비밀번호',

    @Column(length = 15)
    private String u_ph;                    // '사용자 전화번호',

    @Column
    private LocalDate u_sDate;                 // '입사일'

    @Column
    @Enumerated(EnumType.STRING)
    private UserAuthority u_auth;                  // '권한'

    @OneToMany(mappedBy = "user")
    List<Call> callList = new ArrayList<Call>();

    @OneToMany(mappedBy = "user")
    List<Messenger> messengerList = new ArrayList<Messenger>();



    public User(String u_nickname, String u_name, String u_pwd, String u_ph, LocalDate u_sDate, UserAuthority u_auth) {
        this.u_nickname = u_nickname;
        this.u_name = u_name;
        this.u_pwd = u_pwd;
        this.u_ph = u_ph;
        this.u_sDate = u_sDate;
        this.u_auth = u_auth;
    }

    /*
            날짜 : 2022/01/10 2:58 오후
            작성자 : 원보라
            작성내용 : 생성 메서드
    */
    public static User creatUser(UserSaveRequest request){
        User user = new User(request.getU_nickname(), request.getU_name(), request.getU_pwd(), request.getU_ph(), request.getU_sDate(), request.getU_auth());
        return user;
    }

/*
    날짜 : 2022/01/10 1:54 오후
    작성자 : 원보라
    작성내용 : 수정 메서드
*/
    public Boolean updateUser(String u_nickname, String u_name, String u_pwd, String u_ph, LocalDate u_sDate, UserAuthority u_auth) {
        this.u_nickname = u_nickname;
        this.u_name = u_name;
        this.u_pwd = u_pwd;
        this.u_ph = u_ph;
        this.u_sDate = u_sDate;
        this.u_auth = u_auth;
        return true;
    }

}