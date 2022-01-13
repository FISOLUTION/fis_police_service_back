package fis.police.fis_police_server.domain;

import com.sun.istack.NotNull;
import fis.police.fis_police_server.domain.enumType.Participation;
import fis.police.fis_police_server.domain.enumType.Visited;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Center {

    @Id
    @GeneratedValue
    @Column(name = "center_id")
    private Long id;     // 'primary_key',

    @Column(length = 100)
    private String c_sido;        // '시도',

    @Column(length = 100)
    private String c_sigungu;     // '시군구',

    @Column(length = 100)
    private String c_name;        // '시설명',

    @Column(length = 100)
    private String c_type;        // '유형',

    @Column(length = 100)
    private String c_status;      // '운영현황',

    @Column(length = 100)
    private String c_address;     // '주소',

    @Column(length = 100)
    private String c_zipcode;     // '우편번호',

    @Column(length = 100)
    private String c_ph;          // '전화번호',

    @Column(length = 100)
    private String c_faxNum;      // '팩스번호',

    @Column(length = 100)
    private String c_people;      // '현원',

    @Column(length = 100)
    private String c_hpAddress;   // '홈페이지주소',

    @Column(length = 100)
    private String c_latitude;    // '위도',

    @Column(length = 100)
    private String c_longitude;   // '경도',

    @Enumerated(EnumType.STRING)
    private Participation participation;

    @Enumerated(EnumType.STRING)
    private Visited visited;

    @OneToMany(mappedBy = "center", cascade = CascadeType.PERSIST)
    private List<Call> callList = new ArrayList<Call>();

    @OneToMany(mappedBy = "center", cascade = CascadeType.PERSIST)
    private List<Schedule> scheduleList = new ArrayList<Schedule>();

    /*
        날짜 : 2022/01/11 1:27 오후
        작성자 : 현승구
        작성내용 : 테스트용 생성자
    */
    public Center(String c_name, String c_address, String c_ph){
        this.c_name = c_name;
        this.c_address = c_address;
        this.c_ph = c_ph;
    }

    public Center(String c_name, Participation participation){
        this.c_name = c_name;
        this.participation = participation;
    }


}
