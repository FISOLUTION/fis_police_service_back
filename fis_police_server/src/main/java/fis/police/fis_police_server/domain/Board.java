package fis.police.fis_police_server.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 알림장
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    // 글 제목, 내용, 첨부파일
    private String title;
    @Lob
    private String content;
    private String file;

    private String registration_date;
    private String modify_date;
    private String delete_date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Officials officials;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aclass aclass;

    @OneToMany(mappedBy = "board")
    private List<Child> childList = new ArrayList<Child>();

}
