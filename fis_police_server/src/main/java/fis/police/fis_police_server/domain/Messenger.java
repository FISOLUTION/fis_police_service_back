package fis.police.fis_police_server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
public class Messenger {

    @Id @GeneratedValue
    @Column(name = "messenger_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  @JoinColumn(name = "user_id")
    private User user;

    @Column @Lob
    private String context;

    @Column
    private LocalDateTime sendTime;

}
