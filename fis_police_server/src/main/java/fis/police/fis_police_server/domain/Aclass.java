package fis.police.fis_police_server.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Aclass {

    @Id
    @GeneratedValue
    @Column(name = "aclass_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Center center;

    @OneToMany(mappedBy = "aclass")
    private List<Officials> officialsList = new ArrayList<Officials>();

    @OneToMany(mappedBy = "aclass")
    private List<Board> boardList = new ArrayList<Board>();

    @OneToMany(mappedBy = "aclass")
    private List<Child> childList = new ArrayList<Child>();

    @OneToMany(mappedBy = "aclass")
    private List<Calendar> calendarList = new ArrayList<Calendar>();

    @OneToMany(mappedBy = "aclass")
    private List<Announce> announceList = new ArrayList<Announce>();
}
