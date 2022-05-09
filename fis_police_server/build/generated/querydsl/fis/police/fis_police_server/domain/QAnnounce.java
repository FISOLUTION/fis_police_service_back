package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnnounce is a Querydsl query type for Announce
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnnounce extends EntityPathBase<Announce> {

    private static final long serialVersionUID = -1134719640L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnnounce announce = new QAnnounce("announce");

    public final QAclass aclass;

    public final EnumPath<fis.police.fis_police_server.domain.enumType.AnnounceType> announceType = createEnum("announceType", fis.police.fis_police_server.domain.enumType.AnnounceType.class);

    public final ListPath<Child, QChild> childList = this.<Child, QChild>createList("childList", Child.class, QChild.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final StringPath delete_date = createString("delete_date");

    public final StringPath delete_time = createString("delete_time");

    public final BooleanPath external = createBoolean("external");

    public final StringPath file = createString("file");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath modify_date = createString("modify_date");

    public final StringPath modify_time = createString("modify_time");

    public final QOfficials officials;

    public final StringPath registration_date = createString("registration_date");

    public final StringPath registration_time = createString("registration_time");

    public final StringPath title = createString("title");

    public QAnnounce(String variable) {
        this(Announce.class, forVariable(variable), INITS);
    }

    public QAnnounce(Path<? extends Announce> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnnounce(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnnounce(PathMetadata metadata, PathInits inits) {
        this(Announce.class, metadata, inits);
    }

    public QAnnounce(Class<? extends Announce> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aclass = inits.isInitialized("aclass") ? new QAclass(forProperty("aclass"), inits.get("aclass")) : null;
        this.officials = inits.isInitialized("officials") ? new QOfficials(forProperty("officials"), inits.get("officials")) : null;
    }

}

