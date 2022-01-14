package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.User;
import fis.police.fis_police_server.domain.enumType.HasCar;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CenterSelectDateResponseDTO {
    private Long id;         //'primary_key'
    private String a_name;                             //'현장 요원 이름'
    private String a_ph;                               //'현장 요원 전화번호',
    private String a_code;                             //'현장 요원 코드'
    private String a_address;                          //'현장 요원 집 주소',
    private HasCar a_hasCar;                            //'자차 여부'
    private String a_equipment;                         //'장비 번호'
    private LocalDateTime a_receiveDate;                //'장비 수령 날짜'
    private Float a_latitude;                          //'현장 요원 위도',
    private Float a_longitude;                         //'현장 요원 경도',
    private List<ScheduleDTO> scheduleList = new ArrayList<ScheduleDTO>();

    public CenterSelectDateResponseDTO(Agent agent){
        this.id = agent.getId();
        this.a_name = agent.getA_name();
        this.a_ph = agent.getA_ph();
        this.a_code = agent.getA_code();
        this.a_address = agent.getA_address();
        this.a_hasCar = agent.getA_hasCar();
        this.a_equipment = agent.getA_equipment();
        this.a_receiveDate = agent.getA_receiveDate();
        this.a_latitude = agent.getA_latitude();
        this.a_longitude = agent.getA_longitude();
        agent.getScheduleList().stream()
                .map(e -> new ScheduleDTO(e))
                .collect(Collectors.toList());
    }

    private static class ScheduleDTO{
        private Long id;
        private CenterDTO center;
        private LocalDate visit_date;               // '방문날짜'
        private LocalTime visit_time;               // '방문시간'
        private Integer estimate_num;             // '예상인원'
        private String center_etc;               // 시설 특이사항
        private String agent_etc;                // 현장요원 특이사항
        private String total_etc;               // 비고

        public ScheduleDTO(Schedule schedule) {
            this.id = schedule.getId();
            this.center = new CenterDTO(schedule.getCenter().getC_name(),schedule.getCenter().getC_latitude(),schedule.getCenter().getC_longitude());
            this.visit_date = schedule.getVisit_date();
            this.visit_time = schedule.getVisit_time();
            this.estimate_num = schedule.getEstimate_num();
            this.center_etc = schedule.getCenter_etc();
            this.agent_etc = schedule.getAgent_etc();
            this.total_etc = schedule.getTotal_etc();
        }
    }

    @AllArgsConstructor
    private static class CenterDTO{
        private String c_name;
        private Double a_latitude;                          //'현장 요원 위도',
        private Double a_longitude;                         //'현장 요원 경도',
    }


}
