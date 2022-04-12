package fis.police.fis_police_server.domain;


import javax.persistence.*;

@Entity
@Subselect(
        "select user_id, count(call_id) as count " +
        "from calls " +
        "group by user_id, date"
)
@Immutable
@Synchronize("calls")
public class CallView {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "count")
    private Long count;
}
