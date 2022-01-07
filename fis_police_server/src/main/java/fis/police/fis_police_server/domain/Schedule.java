package fis.police.fis_police_server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
@Getter
public class Schedule {

    @Id @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "agent_id")
    private Agent agent;

    @Column
    private LocalDateTime receipt_date;             // '접수일'

    @Column
    private LocalDate visit_date;               // '방문날짜'

    @Column
    private LocalTime visit_time;               // '방문시간'

    @Column(length = 100)
    private Integer estimate_num;             // '예상인원'

    @Column @Lob
    private String center_etc;               // 시설 특이사항

    @Column @Lob
    private String agent_etc;                // 현장요원 특이사항

    @Column @Lob
    private String total_etc;               // 비고

}
