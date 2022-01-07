package fis.police.fis_police_server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Agent {

    @Id
    @GeneratedValue
    @Column
    private Long agent_id;         //'primary_key'

    @Column(length = 100)
    private String a_name;                          //'현장 요원 이름'

    @Column(length = 100)
    private String a_ph;                            //'현장 요원 전화번호',

    @Column(length = 100)
    private String a_code;                          //'현장 요원 코드'

    @Column(length = 100)
    private String a_address;                       //'현장 요원 집 주소',

    @Column
    private Boolean a_hasCar;                          //'자차 여부'

    @Column(length = 100)
    private String a_equipment;                     //'장비 번호'

    @Column(length = 100)
    private String a_receiveDate;                   //'장비 수령 날짜'

    @Column(length = 100)
    private String a_latitude;                      //'현장 요원 위도',

    @Column(length = 100)
    private String a_longitude;                     //'현장 요원 경도',

    @Column(length = 100)
    private String a_status;                        //'퇴사 여부'





}
