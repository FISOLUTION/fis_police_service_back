package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agent_id")
    private Long id;         //'primary_key'

    @OneToMany(mappedBy = "agent")
    private List<Schedule> scheduleList = new ArrayList<Schedule>();

    @Column(length = 100)
    private String a_name;                          //'현장 요원 이름'

    @Column(length = 100)
    private String a_ph;                            //'현장 요원 전화번호',

    @Column(length = 100)
    private String a_code;                          //'현장 요원 코드'

    @Column(length = 100)
    private String a_address;                       //'현장 요원 집 주소',

    @Enumerated(EnumType.STRING)
    private HasCar a_hasCar;                          //'자차 여부'

    @Column(length = 100)
    private String a_equipment;                     //'장비 번호'

    private LocalDateTime a_receiveDate;                   //'장비 수령 날짜'

    @Column(length = 100)
    private String a_latitude;                      //'현장 요원 위도',

    @Column(length = 100)
    private String a_longitude;                     //'현장 요원 경도',

    @Enumerated(EnumType.STRING)
    private AgentStatus a_status;                        //'퇴사 여부'

}
