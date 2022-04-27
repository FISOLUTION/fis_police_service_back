package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.AnnounceType;
import fis.police.fis_police_server.dto.AnnounceDeleteRequest;
import fis.police.fis_police_server.dto.AnnounceModifyRequest;
import fis.police.fis_police_server.dto.AnnounceSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 *    날짜 : 2022/04/06 1:51 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Announce {

    @Id
    @GeneratedValue
    @Column(name = "announce_id")
    private Long id;

    private String title;
    @Lob
    private String content;
    private String file;

    private String registration_date;
    private String registration_time;

    private String modify_date;
    private String modify_time;

    private String delete_date;
    private String delete_time;

    //외부공개 여부
    private Boolean external;

    //글 종류
    @Enumerated(EnumType.STRING)
    private AnnounceType announceType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "official_id")
    private Officials officials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aclass_id")
    private Aclass aclass;

    //todo 투표기능 만들 때 투표한 child lsit
    @OneToMany(mappedBy = "announce")
    private List<Child> childList = new ArrayList<Child>();


    //연관관계 매소드
    public void mappingOfficials(Officials officials) {
        this.officials = officials;
        officials.getAnnounceList().add(this);
    }
    public void mappingAclass(Aclass aclass) {
        this.aclass = aclass;
        aclass.getAnnounceList().add(this);
    }

    //알림장 게시글 저장
    public static Announce createAnnounce(Officials officials, Aclass aclass, AnnounceSaveRequest announceSaveRequest) {
        Announce announce = new Announce();
        announce.mappingOfficials(officials);
        announce.mappingAclass(aclass);
        announce.title = announceSaveRequest.getTitle();
        announce.content = announceSaveRequest.getContent();
        announce.file = announceSaveRequest.getFile();
        announce.registration_date = announceSaveRequest.getRegistration_date();
        announce.registration_time = announceSaveRequest.getRegistration_time();
        announce.external = announceSaveRequest.getExternal();
        announce.announceType = announceSaveRequest.getAnnounceType();
        return announce;
    }

    //알림장 게시글 수정
    public void updateAnnounce(Aclass aclass, AnnounceModifyRequest announceModifyRequest) {
        this.mappingAclass(aclass);
        this.title=announceModifyRequest.getTitle();
        this.content=announceModifyRequest.getContent();
        this.file=announceModifyRequest.getFile();
        this.modify_date=announceModifyRequest.getModify_date();
        this.modify_time=announceModifyRequest.getModify_time();
        this.external = announceModifyRequest.getExternal();
        this.announceType = announceModifyRequest.getAnnounceType();
    }

    //알림장 게시글 삭제
    public void deleteAnnounce(AnnounceDeleteRequest announceDeleteRequest) {
        this.delete_date = announceDeleteRequest.getDelete_date();
        this.delete_time = announceDeleteRequest.getDelete_time();
    }

}
