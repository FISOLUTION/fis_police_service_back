package fis.police.fis_police_server.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Announce {

    @Id
    @GeneratedValue
    @Column(name = "announce_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Officials officials;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aclass aclass;

    @OneToMany(mappedBy = "announce")
    private List<Child> childList = new ArrayList<Child>();
}
