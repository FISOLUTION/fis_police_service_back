package fis.police.fis_police_server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@DiscriminatorValue("null")
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserTeacher {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(length = 100, name = "name")
    protected String o_name;

    @Column(length = 100, name = "phoneNumber")
    protected String o_ph;

    @Column(length = 100, name = "emailAddress")
    protected String o_email;

    @Column(length = 100, name = "loginId")
    protected String o_nickname;

    @Column(length = 100, name = "password")
    protected String o_pwd;
}
