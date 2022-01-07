package fis.police.fis_police_server.domain;

import javax.persistence.Column;

public class User {


    user_id         BIGINT              // 'primary_key',

    @Column(length = 100)
    private String u_nickname;              // "사용자 id"

    @Column(length = 100)
    private String u_name;                  // '사용자 이름',

    @Column(length = 100)
    private String u_pwd;                   // '사용자 비밀번호',

    @Column(length = 100)
    private String u_ph;                    // '사용자 전화번호',

    @Column(length = 100)
    private String u_sDate;                 // '입사일'

    @Column(length = 100)
    private String u_auth;                  // '권한'

}
