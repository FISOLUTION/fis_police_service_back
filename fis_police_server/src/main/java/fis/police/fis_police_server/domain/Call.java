package fis.police.fis_police_server.domain;


import fis.police.fis_police_server.domain.enumType.InOut;
import fis.police.fis_police_server.domain.enumType.Participation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
public class Call {

    @Id
    @GeneratedValue
    @Column(name = "call_id")
    private Long id;         //BIGINT                 NOT NULL    AUTO_INCREMENT      comment 'primary_key',

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "center_id")
    private Center center;      // BIGINT              NOT NULL                        comment 'center_id',

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;        // BIGINT                 NOT NULL                        comment 'user_id',

    private LocalDateTime dateTime;       //'입력날짜 및 시간',

    @Column(length = 100)
    private Participation participation;  // '참여여부(참여/거부/보류/기타)',

    @Column(length = 100) @Enumerated(EnumType.STRING)
    private InOut in_out;          // '접수방법',

    @Column(length = 100)
    private String c_manager;      //'시설 담당자 성명',

    @Column(length = 100)
    private String m_ph;           //시설 담당자 전화번호',

    @Column(length = 100)
    private String m_email;       // '시설 담당자 이메일 ',

    @Lob
    private String center_etc;    // '시설 특이사항

    @Lob
    private String agent_etc;     // '현장요원 특이사항

}
