package fis.police.fis_police_server.domain;

import fis.police.fis_police_server.domain.enumType.Complete;
import fis.police.fis_police_server.dto.ConfirmFromAgentRequest;
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

    private String visit_date;
    private String visit_time;

    private String new_child;
    private String old_child;   // 기존 아동

    private String senile;  // 치매 환자
    private String disabled;    // 지적장애

    @Column
    @Lob
    private String etc;

    @Enumerated(EnumType.STRING)
    private Complete complete;  // 시설이 확인했는지 여부

    public static Confirm createConfirm(ConfirmFromAgentRequest request, Agent agent, Center center) {
        Confirm confirm = new Confirm();
        confirm.agent = agent;
        confirm.center = center;
        confirm.visit_date = request.getVisit_date();
        confirm.visit_time = request.getVisit_time();
        confirm.new_child = request.getNew_child();
        confirm.old_child = request.getOld_child();
        confirm.senile = request.getSenile();
        confirm.disabled = request.getDisabled();
        confirm.etc = request.getEtc();
        confirm.complete = request.getComplete();

        return confirm;
    }


}
