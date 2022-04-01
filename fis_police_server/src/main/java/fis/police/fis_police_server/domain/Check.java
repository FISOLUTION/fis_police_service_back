package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.dto.CheckRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Checks")
public class Check {
    @Id
    @GeneratedValue
    @Column(name = "check_id")
    private Long id;

    private String check_date; //확인날짜
    private String check_time; //확인시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;


    //연관관계 매소드
    public void mappingBoard(Board board) {
        this.board = board;
        board.getCheckList().add(this);
    }

    public void mappingChild(Child child) {
        this.child = child;
        child.getCheckList().add(this);
    }

    public static Check createCheck(Board board, Child child, CheckRequest checkRequest) {
        Check check = new Check();
        check.mappingBoard(board);
        check.mappingChild(child);
        check.check_date = checkRequest.getCheck_date();
        check.check_time = checkRequest.getCheck_time();
        return check;
    }

}
