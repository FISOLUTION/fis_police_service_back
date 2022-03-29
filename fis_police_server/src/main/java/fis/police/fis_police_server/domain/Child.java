package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "child_id")
    private Long id;

    // 로그인 정보
    private String c_nickname;
    private String c_pwd;

    // 아이 정보
    private String name;
    private String birthday;
    private String parent;

    // 승인 여부
    private Accept accept;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aclass aclass;

    @ManyToOne(fetch = FetchType.LAZY)
    private Announce announce;

    @Column(columnDefinition="varchar(32) default 'CHILD'")
    @Enumerated(EnumType.STRING)
    private UserAuthority authority;
}
