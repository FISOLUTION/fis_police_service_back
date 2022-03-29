package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.dto.BoardDeleteRequest;
import fis.police.fis_police_server.dto.BoardModifyRequest;
import fis.police.fis_police_server.dto.BoardSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 알림장
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    // 글 제목, 내용, 첨부파일
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "official_id")
    private Officials officials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aclass_id")
    private Aclass aclass;

    @OneToMany(mappedBy = "board")
    private List<Child> childList = new ArrayList<Child>();



    //연관관계 매소드
    public void mappingOfficials(Officials officials) {
        this.officials = officials;
        officials.getBoardList().add(this);
    }

    public void mappingAclass(Aclass aclass) {
        this.aclass = aclass;
        aclass.getBoardList().add(this);
    }

    //알림장 게시글 저장
    public static Board createBoard(Officials officials, Aclass aclass, BoardSaveRequest boardSaveRequest) {
        Board board = new Board();
        board.mappingOfficials(officials);
        board.mappingAclass(aclass);
        board.title = boardSaveRequest.getTitle();
        board.content = boardSaveRequest.getContent();
        board.file = boardSaveRequest.getFile();
        board.registration_date = boardSaveRequest.getRegistration_date();
        board.registration_time = boardSaveRequest.getRegistration_time();
        return board;
    }

    //알림장 게시글 수정
    public void updateBoard(Aclass aclass, BoardModifyRequest boardModifyRequest) {
        this.mappingAclass(aclass);
        this.title=boardModifyRequest.getTitle();
        this.content=boardModifyRequest.getContent();
        this.file=boardModifyRequest.getFile();
        this.modify_date=boardModifyRequest.getModify_date();
        this.modify_time=boardModifyRequest.getModify_time();
    }

    //알림장 게시글 삭제
    public void deleteBoard(BoardDeleteRequest boardDeleteRequest){
        this.delete_date=boardDeleteRequest.getDelete_date();
        this.delete_time=boardDeleteRequest.getDelete_time();
    }
}
