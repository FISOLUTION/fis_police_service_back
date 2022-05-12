package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOfficials is a Querydsl query type for Officials
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOfficials extends EntityPathBase<Officials> {

    private static final long serialVersionUID = -107353655L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOfficials officials = new QOfficials("officials");

    public final QUserTeacher _super = new QUserTeacher(this);

    public final EnumPath<fis.police.fis_police_server.domain.enumType.Accept> accept = createEnum("accept", fis.police.fis_police_server.domain.enumType.Accept.class);

    public final QAclass aclass;

    public final ListPath<Announce, QAnnounce> announceList = this.<Announce, QAnnounce>createList("announceList", Announce.class, QAnnounce.class, PathInits.DIRECT2);

    public final ListPath<Board, QBoard> boardList = this.<Board, QBoard>createList("boardList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final ListPath<Calendar, QCalendar> calendarList = this.<Calendar, QCalendar>createList("calendarList", Calendar.class, QCalendar.class, PathInits.DIRECT2);

    public final QCenter center;

    public final ListPath<Hope, QHope> hopeList = this.<Hope, QHope>createList("hopeList", Hope.class, QHope.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath o_email = _super.o_email;

    //inherited
    public final StringPath o_name = _super.o_name;

    //inherited
    public final StringPath o_nickname = _super.o_nickname;

    //inherited
    public final StringPath o_ph = _super.o_ph;

    //inherited
    public final StringPath o_pwd = _super.o_pwd;

    public final EnumPath<fis.police.fis_police_server.domain.enumType.UserAuthority> u_auth = createEnum("u_auth", fis.police.fis_police_server.domain.enumType.UserAuthority.class);

    public QOfficials(String variable) {
        this(Officials.class, forVariable(variable), INITS);
    }

    public QOfficials(Path<? extends Officials> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOfficials(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOfficials(PathMetadata metadata, PathInits inits) {
        this(Officials.class, metadata, inits);
    }

    public QOfficials(Class<? extends Officials> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aclass = inits.isInitialized("aclass") ? new QAclass(forProperty("aclass"), inits.get("aclass")) : null;
        this.center = inits.isInitialized("center") ? new QCenter(forProperty("center"), inits.get("center")) : null;
    }

}

