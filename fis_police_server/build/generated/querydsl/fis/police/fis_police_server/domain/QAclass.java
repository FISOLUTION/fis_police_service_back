package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAclass is a Querydsl query type for Aclass
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAclass extends EntityPathBase<Aclass> {

    private static final long serialVersionUID = 1360652918L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAclass aclass = new QAclass("aclass");

    public final ListPath<Announce, QAnnounce> announceList = this.<Announce, QAnnounce>createList("announceList", Announce.class, QAnnounce.class, PathInits.DIRECT2);

    public final ListPath<Board, QBoard> boardList = this.<Board, QBoard>createList("boardList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final ListPath<Calendar, QCalendar> calendarList = this.<Calendar, QCalendar>createList("calendarList", Calendar.class, QCalendar.class, PathInits.DIRECT2);

    public final QCenter center;

    public final ListPath<Child, QChild> childList = this.<Child, QChild>createList("childList", Child.class, QChild.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Officials, QOfficials> officialsList = this.<Officials, QOfficials>createList("officialsList", Officials.class, QOfficials.class, PathInits.DIRECT2);

    public QAclass(String variable) {
        this(Aclass.class, forVariable(variable), INITS);
    }

    public QAclass(Path<? extends Aclass> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAclass(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAclass(PathMetadata metadata, PathInits inits) {
        this(Aclass.class, metadata, inits);
    }

    public QAclass(Class<? extends Aclass> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.center = inits.isInitialized("center") ? new QCenter(forProperty("center")) : null;
    }

}

