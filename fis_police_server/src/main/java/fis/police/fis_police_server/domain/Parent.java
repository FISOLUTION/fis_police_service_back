package fis.police.fis_police_server.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "parent_id")
    public Long id;

    public String p_nickname;
    public String p_pwd;

    @OneToMany(mappedBy = "parent")
    private List<Child> childList = new ArrayList<Child>();
}
