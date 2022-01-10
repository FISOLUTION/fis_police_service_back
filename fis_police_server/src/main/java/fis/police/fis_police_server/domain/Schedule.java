package fis.police.fis_police_server.domain;

import javax.persistence.Column;

public class Schedule {

    @Column(length = 100)
    private String receipt_date;             // '접수일'

    @Column(length = 100)
    private String visit_date;               // '방문날짜'

    @Column(length = 100)
    private String visit_time;               // '방문시간'

    @Column(length = 100)
    private String estimate_num;             // '예상인원'

    @Column(length = 300)
    private String center_etc;               // '기타 및 비고'

    @Column(length = 300)
    private String agent_etc;                // '기타 및 비고'
}
