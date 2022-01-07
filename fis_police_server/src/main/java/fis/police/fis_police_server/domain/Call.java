package fis.police.fis_police_server.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Call {

    @Id
    @GeneratedValue
    @Column
    private Long call_id;         BIGINT                 NOT NULL    AUTO_INCREMENT      comment 'primary_key',

    private Long center_id;       BIGINT              NOT NULL                        comment 'center_id',
    private Long user_id;         BIGINT                 NOT NULL                        comment 'user_id',


    @Column(length = 100)
    private String dateTime;       //'입력날짜 및 시간',

    @Column(length = 100)
    private String participation;  // '참여여부(참여/거부/보류/기타)',

    @Column(length = 100)
    private String in_out;          // '접수방법',

    @Column(length = 100)
    private String c_manager;      //'시설 담당자 성명',

    @Column(length = 100)
    private String m_ph;           //시설 담당자 전화번호',

    @Column(length = 100)
    private String m_email;       // '시설 담당자 이메일 ',

    @Column(length = 300)
    private String center_etc;    // '기타 및 비고',

    @Column(length = 300)
    private String agent_etc;     // '기타 및 비고',

}
