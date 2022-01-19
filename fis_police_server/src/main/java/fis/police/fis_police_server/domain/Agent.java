package fis.police.fis_police_server.domain;

import com.sun.istack.NotNull;
import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agent_id")
    private Long id;         //'primary_key'

    @OneToMany(mappedBy = "agent")
    private List<Schedule> scheduleList = new ArrayList<Schedule>();

    @Column(length = 100)
    private String a_name;                             //'현장 요원 이름'

    @Column(length = 100)
    private String a_ph;                               //'현장 요원 전화번호',

    // 작성자 : 이승범  유니크 속성 걸어줘야 됨
    @Column(length = 100)
    private String a_code;                             //'현장 요원 코드'

    @Column(length = 100)
    private String a_address;                          //'현장 요원 집 주소',

    @Enumerated(EnumType.STRING)
    private HasCar a_hasCar;                            //'자차 여부'

    @Column(length = 100)
    private String a_equipment;                         //'장비 번호'

    private LocalDateTime a_receiveDate;                //'장비 수령 날짜'

    private Double a_latitude;                          //'현장 요원 위도',
    private Double a_longitude;                         //'현장 요원 경도',

    @Enumerated(EnumType.STRING)
    private AgentStatus a_status;                       //'퇴사 여부'


    /*
        작성날짜: 2022/01/11 5:05 PM
        작성자: 이승범
        작성내용: AgentService 구현을 위한 연관관계 메서드 및 setter 구현
    */
    // 생성 메서드
    public static Agent createAgent(String a_name, String a_ph, String a_code, String a_address, HasCar a_hasCar,
                             String a_equipment, LocalDateTime a_receiveDate, Double a_latitude, Double a_longitude) {
        Agent agent = new Agent();
        agent.a_name = a_name;
        agent.a_ph = a_ph;
        agent.a_code = a_code;
        agent.a_address = a_address;
        agent.a_hasCar = a_hasCar;
        agent.a_equipment = a_equipment;
        agent.a_receiveDate = a_receiveDate;
        agent.a_latitude = a_latitude;
        agent.a_longitude = a_longitude;
        agent.a_status = AgentStatus.WORK;
        return agent;
    }

    // 현장요원 정보 수정을 위한 setter
    public void modifyAgent(String a_name, String a_ph, String a_code, String a_address, HasCar a_hasCar, String a_equipment,
                            LocalDateTime a_receiveDate, Double a_latitude, Double a_longitude, AgentStatus a_status){
        this.a_name = a_name;
        this.a_ph = a_ph;
        this.a_code = a_code;
        this.a_address = a_address;
        this.a_hasCar = a_hasCar;
        this.a_equipment = a_equipment;
        this.a_receiveDate = a_receiveDate;
        this.a_latitude = a_latitude;
        this.a_longitude = a_longitude;
        this.a_status = a_status;
    }
}
