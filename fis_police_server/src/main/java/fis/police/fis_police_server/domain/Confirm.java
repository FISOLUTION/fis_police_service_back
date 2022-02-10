package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.Complete;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
public class Confirm {

    @Id @GeneratedValue
    @Column(name = "confirm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private String new_child;
    private String old_child;   // 기존 아동

    private String senile;  // 치매 환자
    private String disabled;    // 지적장애

    @Column
    @Lob
    private String etc;

    @Enumerated(EnumType.STRING)
    private Complete complete;  // 시설이 확인했는지 여부
}
