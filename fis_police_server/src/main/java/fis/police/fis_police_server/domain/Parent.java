package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.ParentSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "CALL_PARENT")
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "parent_id")
    private Long id;

    private String p_nickname;
    private String p_pwd;

    private String name;
    private String ph;
    private String email;

    @OneToMany(mappedBy = "parent")
    private List<Child> childList = new ArrayList<Child>();

    @Enumerated(EnumType.STRING)
    private UserAuthority u_auth;

    public static Parent createParent(ParentSaveRequest request) {
        Parent parent = new Parent();
        parent.p_nickname = request.getNickname();
        parent.p_pwd = request.getPwd();
        parent.name = request.getName();
        parent.ph = request.getPh();
        parent.email = request.getEmail();
        parent.u_auth = UserAuthority.PARENT;
        return parent;
    }
}
