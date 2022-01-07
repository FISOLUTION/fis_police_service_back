package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.UserAuthority;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class User {

    @Id @GeneratedValue @Column(name = "user_id")
    private Long id;
    //user_id         BIGINT              // 'primary_key',

    @Column(length = 100)
    private String u_nickname;              // "사용자 id"

    @Column(length = 6)
    private String u_name;                  // '사용자 이름',

    @Column(length = 100)
    private String u_pwd;                   // '사용자 비밀번호',

    @Column(length = 15)
    private String u_ph;                    // '사용자 전화번호',

    @Column
    private LocalDate u_sDate;                 // '입사일'

    @Column @Enumerated(EnumType.STRING)
    private UserAuthority u_auth;                  // '권한'

    @OneToMany(mappedBy = "user")
    List<Call> callList = new ArrayList<Call>();

    @OneToMany(mappedBy = "user")
    List<Messenger> messengerList = new ArrayList<Messenger>();
}
