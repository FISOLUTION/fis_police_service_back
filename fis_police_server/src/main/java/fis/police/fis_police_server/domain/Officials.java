package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.dto.OfficialSaveRequest;
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

    @OneToMany(mappedBy = "officials")
    private List<Hope> hopeList = new ArrayList<Hope>();


    /*
        작성 날짜: 2022/02/14 11:37 오전
        작성자: 고준영
        작성 내용: 시설 담당자, 시설 묶기
    */
    public static Officials createOfficials(OfficialSaveRequest request, Center center) {
        Officials officials = new Officials();
        officials.o_name = request.getO_name();
        officials.o_ph = request.getO_ph();
        officials.o_email = request.getO_email();
        officials.o_nickname = request.getO_nickname();
        officials.o_pwd = request.getO_pwd();
        officials.center = center;

        return officials;
    }
}
