package fis.police.fis_police_server.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    /*
        날짜 : 2022/01/11 5:24 오후
        작성자 : 현승구
        작성내용 : test 코드를 위한 constructor
    */
    public Schedule(Center center, String center_etc) {
        this.center = center;
        this.center_etc = center_etc;
    }

    /*
        작성날짜: 2022/01/12 4:43 PM
        작성자: 이승범
        작성내용: ScheduleService 구현을 위한  연관관계 메서드 및 생성자 구현
    */
    public static Schedule createSchedule(Center center, User user, Agent agent, LocalDateTime receipt_date,
                                          LocalDate visit_date, LocalTime visit_time, Integer estimate_num,
                                          String center_etc, String agent_etc, String total_etc){
        Schedule schedule = new Schedule();
        schedule.mappingCenter(center);
        schedule.mappingUser(user);
        schedule.mappingAgent(agent);
        schedule.receipt_date = receipt_date;
        schedule.visit_date = visit_date;
        schedule.visit_time = visit_time;
        schedule.estimate_num = estimate_num;
        schedule.center_etc = center_etc;
        schedule.agent_etc = agent_etc;
        schedule.total_etc = total_etc;
        return schedule;
    }
    // ============ 연관관계 메서드 ===============
    public void mappingCenter(Center center){
        this.center = center;
        center.getScheduleList().add(this);
    }
    public void mappingUser(User user){
        this.user = user;
    }
    public void mappingAgent(Agent agent){
        this.agent = agent;
        agent.getScheduleList().add(this);
    }
}
