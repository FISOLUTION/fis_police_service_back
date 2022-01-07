package fis.police.fis_police_server.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
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

    @OneToMany(mappedBy = "center")
    private List<Call> callList = new ArrayList<Call>();

    @OneToMany(mappedBy = "center")
    private List<Schedule> scheduleList = new ArrayList<Schedule>();

}
