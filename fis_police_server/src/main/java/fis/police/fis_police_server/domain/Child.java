package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.ChildModifyRequest;
import fis.police.fis_police_server.dto.ChildSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CALL_CHILD")
@Getter
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "child_id")
    private Long id;

    // 아이 정보
    private String name;
    private String birthday;

    // 승인 여부
    @Enumerated(EnumType.STRING)
    private Accept accept;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "board_id")
//    private Board board;
    @OneToMany(mappedBy = "child")
    private List<Check> checkList = new ArrayList<Check>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aclass_id")
    private Aclass aclass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announce_id")
    private Announce announce;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Column(columnDefinition="varchar(32) default 'CHILD'")
    @Enumerated(EnumType.STRING)
    private UserAuthority u_auth;

    public static Child createChild(ChildSaveRequest request, Parent parent, Aclass aclass) {
        Child child = new Child();
        child.name = request.getChild_name();
        child.birthday = request.getChild_birth();
        // 아래 두개 연관관계 매핑으로 묶어야하겠지???
        child.aclass = aclass;
        child.mappingParent(parent);
        child.accept = Accept.WAITING;
        return child;
    }

    public void mappingParent(Parent parent) {
        this.parent = parent;
        parent.getChildList().add(this);
    }

    public void modifyChild(ChildModifyRequest request, Aclass aclass) {
        this.name = request.getName();
        this.birthday = request.getBirthday();
        this.aclass = aclass;
    }

    public void acceptChild(Accept accept) {
        this.accept = accept;
    }


    //for init db
    public Child(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
        this.accept = Accept.WAITING;
    }

}
