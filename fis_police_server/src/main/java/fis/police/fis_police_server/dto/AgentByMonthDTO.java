package fis.police.fis_police_server.dto;

import com.querydsl.core.annotations.QueryProjection;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Schedule;
import fis.police.fis_police_server.domain.enumType.HasCar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AgentByMonthDTO {
    private Long agent_id;         //'primary_key'
    private String a_name;                             //'현장 요원 이름'
    private String a_ph;                               //'현장 요원 전화번호',
    private String a_code;                             //'현장 요원 코드'
    private String a_address;                          //'현장 요원 집 주소',
    private HasCar a_hasCar;                            //'자차 여부'
    private String a_equipment;                         //'장비 번호'
    private LocalDate a_receiveDate;                //'장비 수령 날짜'
    private Double a_latitude;                          //'현장 요원 위도',
    private Double a_longitude;                         //'현장 요원 경도',
    private List<ScheduleByDTO> scheduleList = new ArrayList<ScheduleByDTO>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScheduleByDTO {
        private LocalDate date;
        private List<ScheduleDTO> scheduleList;
    }

    @QueryProjection
    public AgentByMonthDTO(Agent agent) {
        this.agent_id = agent.getId();
        this.a_name = agent.getA_name();
        this.a_ph = agent.getA_ph();
        this.a_code = agent.getA_code();
        this.a_address = agent.getA_address();
        this.a_hasCar = agent.getA_hasCar();
        this.a_equipment = agent.getA_equipment();
        this.a_receiveDate = agent.getA_receiveDate();
        this.a_latitude = agent.getA_latitude();
        this.a_longitude = agent.getA_longitude();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScheduleDTO {
        private Long center_id;
        private LocalDate receipt_date;             // '접수일'
        private LocalDate visit_date;               // '방문날짜'
        private LocalTime visit_time;               // '방문시간'
        private Integer estimate_num;             // '예상인원'
        private String center_etc;               // 시설 특이사항
        private String agent_etc;                // 현장요원 특이사항
        private String total_etc;               // 스케쥴 특이사항
        private String call_check;              // 최근 통화 상태
        private String call_check_info;         // 최근 통화 상태 설명
        private String modified_info;           // 변경 사항

        public ScheduleDTO(Schedule s) {
            this.center_id = s.getId();
            this.receipt_date = s.getReceipt_date();
            this.visit_date = s.getVisit_date();
            this.visit_time = s.getVisit_time();
            this.estimate_num = s.getEstimate_num();
            this.center_etc = s.getCenter_etc();
            this.agent_etc = s.getAgent_etc();
            this.total_etc = s.getTotal_etc();
            this.call_check = s.getCall_check();
            this.call_check_info = s.getCall_check_info();
            this.modified_info = s.getModified_info();
        }
    }
}
