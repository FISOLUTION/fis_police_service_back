package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *    날짜 : 2022/04/05 3:30 오후
 *    작성자 : 원보라
 *    작성내용 : 연관관계 메소드 및 생성 수정
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Calendar {

    @Id
    @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;

    // 진짜 일정 날짜
    private String date;
    private String title;
    private String content;

    private String registration_date;
    private String modify_date;
    private String delete_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "official_id")
    private Officials officials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aclass_id")
    private Aclass aclass;

    //연관관계 매소드
    public void mappingOfficials(Officials officials) {
        this.officials = officials;
        officials.getCalendarList().add(this);
    }

    public void mappingAclass(Aclass aclass) {
        this.aclass = aclass;
        aclass.getCalendarList().add(this);
    }

    //일정표 저장
    public static Calendar createCalendar(Officials officials, Aclass aclass, CalendarSaveRequest calendarSaveRequest) {
        Calendar calendar = new Calendar();
        calendar.mappingOfficials(officials);
        calendar.mappingAclass(aclass);
        calendar.date = calendarSaveRequest.getDate();
        calendar.title = calendarSaveRequest.getTitle();
        calendar.content = calendarSaveRequest.getContent();
        calendar.registration_date = calendarSaveRequest.getRegistration_date();
        return calendar;
    }

    //일정표 수정
    public void updateCalendar(Aclass aclass, CalendarModifyRequest calendarModifyRequest) {
        this.mappingAclass(aclass);
        this.date = calendarModifyRequest.getDate();
        this.title=calendarModifyRequest.getTitle();
        this.content=calendarModifyRequest.getContent();
        this.modify_date=calendarModifyRequest.getModify_date();
    }

    //일정표 삭제
    public void deleteCalendar(CalendarDeleteRequest calendarDeleteRequest){
        this.delete_date=calendarDeleteRequest.getDelete_date();
    }
}
