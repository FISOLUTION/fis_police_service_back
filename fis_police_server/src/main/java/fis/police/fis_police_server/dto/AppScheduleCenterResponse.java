package fis.police.fis_police_server.dto;

import com.querydsl.core.annotations.QueryProjection;
import fis.police.fis_police_server.domain.enumType.Accept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class AppScheduleCenterResponse {
    private Long schedule_id;
    private LocalDate visit_date;   //방문 날짜
    private LocalTime visit_time;   //방문시간
    private Integer estimate_num;   //예상인원
    private String center_etc;      // 시설 특이사항
    private String agent_etc;       // 현장요원 특이사항
    private String total_etc;       // 스케쥴 특이사항
    private Accept accept;          //현장요원 일정 수락 여부
    private String late_comment;    //늦는 사유 멘트 현장요원이 선택하면 시설에 띄워주기

    private Double c_latitude;      //위도
    private Double c_longitude;     //경도

    private String a_name;          //현장 요원 이름
    private String a_ph;            //현장 요원 전화번호
    private String a_code;          //현장 요원 코드

    @QueryProjection
    public AppScheduleCenterResponse(Long schedule_id, LocalDate visit_date, LocalTime visit_time, Integer estimate_num, String center_etc, String agent_etc, String total_etc, Accept accept, String late_comment, Double c_latitude, Double c_longitude, String a_name, String a_ph, String a_code) {
        this.schedule_id = schedule_id;
        this.visit_date = visit_date;
        this.visit_time = visit_time;
        this.estimate_num = estimate_num;
        this.center_etc = center_etc;
        this.agent_etc = agent_etc;
        this.total_etc = total_etc;
        this.accept = accept;
        this.late_comment = late_comment;
        this.c_latitude = c_latitude;
        this.c_longitude = c_longitude;
        this.a_name = a_name;
        this.a_ph = a_ph;
        this.a_code = a_code;
    }
}
