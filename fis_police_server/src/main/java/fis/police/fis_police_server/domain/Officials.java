package fis.police.fis_police_server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class Officials {
    @Id
    @GeneratedValue
    @Column(name = "official_id")
    private Long id;

    @Column(length = 100)
    private String o_name;

    @Column(length = 100)
    private String o_ph;

    @Column(length = 100)
    private String o_email;

    @Column(length = 100)
    private String o_nickname;

    @Column(length = 100)
    private String o_pwd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @OneToMany(mappedBy = "hope")
    private List<Hope> hopeList = new ArrayList<Hope>();



}
