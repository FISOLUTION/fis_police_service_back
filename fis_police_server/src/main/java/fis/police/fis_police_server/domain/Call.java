package fis.police.fis_police_server.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import fis.police.fis_police_server.domain.enumType.InOut;
import fis.police.fis_police_server.domain.enumType.Participation;
import fis.police.fis_police_server.dto.CallSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Call {

    @Id
    @GeneratedValue
    @Column(name = "call_id")
    private Long id;         //BIGINT                 NOT NULL    AUTO_INCREMENT      comment 'primary_key',

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;      // BIGINT              NOT NULL                        comment 'center_id',

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;        // BIGINT                 NOT NULL                        comment 'user_id',

    @Column(length = 10)
    private LocalDateTime dateTime;       //'입력날짜 및 시간',

    @Enumerated(EnumType.STRING)
    private Participation participation;  // '참여여부(참여/거부/보류/기타)',

    @Enumerated(EnumType.STRING)
    private InOut in_out;          // '접수방법',

    @Column(length = 100)
    private String c_manager;      //'시설 담당자 성명',

    @Column(length = 100)
    private String m_ph;           //시설 담당자 전화번호',

    @Column(length = 100)
    private String m_email;       // '시설 담당자 이메일 ',

    @Column
    private Integer num;          // '시설 예상인원'

    @Lob
    private String center_etc;    // '시설 특이사항

    @Lob
    private String agent_etc;     // '현장요원 특이사항

/*
    작성 날짜: 2022/01/10 1:14 오후
    작성자: 고준영
    작성 내용: service 계층에서 받아온 api 스펙을 바탕으로 call 객체 생성하는 생성자와 메서드
*/
    public Call(Center center, User user, LocalDateTime dateTime, Participation participation, InOut in_out, String c_manager, String m_ph, String m_email, Integer num, String center_etc, String agent_etc) {
        this.center = center;
        this.user = user;
        this.dateTime = dateTime;
        this.participation = participation;
        this.in_out = in_out;
        this.c_manager = c_manager;
        this.m_ph = m_ph;
        this.m_email = m_email;
        this.num = num;
        this.center_etc = center_etc;
        this.agent_etc = agent_etc;
    }

    public static Call createCall(CallSaveRequest request, Center center, User user) {
        Call call = new Call(center, user, request.getDateTime(), request.getParticipation(),
                request.getIn_out(), request.getC_manager(), request.getM_ph(), request.getM_email(), request.getNum(),
                request.getCenter_etc(), request.getAgent_etc());

        return call;
    }

    /*
        날짜 : 2022/01/11 5:22 오후
        작성자 : 현승구
        작성내용 : test code 위한 constructor
    */

    public Call(Center center) {
        this.center = center;
    }
}
