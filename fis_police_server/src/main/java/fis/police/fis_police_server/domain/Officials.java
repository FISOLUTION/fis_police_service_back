package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.domain.enumType.UserAuthority;
import fis.police.fis_police_server.dto.OfficialSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "teacher")
@OnDelete(action = OnDeleteAction.CASCADE)
@DiscriminatorValue("Teacher")
public class Officials extends UserTeacher{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @OneToMany(mappedBy = "officials")
    private List<Hope> hopeList = new ArrayList<Hope>();

    /*
        작성 날짜: 2022/02/16 4:19 오후
        작성자: 고준영
        작성 내용: 시설관리자 권한 -> OFFICIAL
    */

    //@Enumerated(EnumType.STRING)
    //@Column(name = "auth")
    //private UserAuthority u_auth;                  // '권한'

    // 작성 글
    @OneToMany(mappedBy = "officials")
    private List<Board> boardList = new ArrayList<Board>();
    // 작성 일정
    @OneToMany(mappedBy = "officials")
    private List<Calendar> calendarList = new ArrayList<Calendar>();

    @OneToMany(mappedBy = "officials")
    private List<Announce> announceList = new ArrayList<Announce>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aclass_id")
    private Aclass aclass;

    // 교사 승인 여부
    @Enumerated(EnumType.STRING)
    private Accept accept;


    /*
        작성 날짜: 2022/02/14 11:37 오전
        작성자: 고준영
        작성 내용: 시설 담당자, 시설 묶기
    */
    public static Officials createOfficials(OfficialSaveRequest request, Center center, Accept accept) {
        Officials officials = new Officials();
        officials.o_name = request.getO_name();
        officials.o_ph = request.getO_ph();
        officials.o_email = request.getO_email();
        officials.o_nickname = request.getO_nickname();
        officials.o_pwd = request.getO_pwd();
        officials.center = center;
        officials.u_auth = request.getU_auth();
        officials.accept = accept;
        return officials;
    }

    public void modifyOfficial(OfficialSaveRequest request, Center center) {
        this.o_name = request.getO_name();
        this.o_nickname = request.getO_nickname();
        this.o_pwd = request.getO_pwd();
        this.o_ph = request.getO_ph();
        this.o_email = request.getO_email();
        this.center = center;
    }

    public Officials(Long id, String o_name, String o_ph, String o_email, String o_nickname, String o_pwd, Center center, List<Hope> hopeList, UserAuthority u_auth, List<Board> boardList, List<Calendar> calendarList, List<Announce> announceList, Aclass aclass, Accept accept) {
        super(id, o_name, o_ph, o_email, o_nickname, o_pwd, u_auth);
        this.center = center;
        this.hopeList = hopeList;
        this.u_auth = u_auth;
        this.boardList = boardList;
        this.calendarList = calendarList;
        this.announceList = announceList;
        this.aclass = aclass;
        this.accept = accept;
    }
}
