package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.domain.enumType.Complete;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
public class Hope {

    @Id @GeneratedValue
    @Column(name = "hope_id")
    private Long id;

    // 시설이 적어내는 참여여부
    @Enumerated(EnumType.STRING)
    private Accept accept;

    private String h_date;
    private String h_time;

    // 필요?에 대한 의문
    private String h_mail;
    private String h_ph;

    // 일정 잡기 완료/미완료
    @Enumerated(EnumType.STRING)
    private Complete complete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "official_id")
    private Officials officials;

}
