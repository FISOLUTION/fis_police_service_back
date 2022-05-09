package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChild is a Querydsl query type for Child
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChild extends EntityPathBase<Child> {

    private static final long serialVersionUID = -923945859L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChild child = new QChild("child");

    public final EnumPath<fis.police.fis_police_server.domain.enumType.Accept> accept = createEnum("accept", fis.police.fis_police_server.domain.enumType.Accept.class);

    public final QAclass aclass;

    public final QAnnounce announce;

    public final StringPath birthday = createString("birthday");

    public final ListPath<Check, QCheck> checkList = this.<Check, QCheck>createList("checkList", Check.class, QCheck.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QParent parent;

    public final EnumPath<fis.police.fis_police_server.domain.enumType.UserAuthority> u_auth = createEnum("u_auth", fis.police.fis_police_server.domain.enumType.UserAuthority.class);

    public QChild(String variable) {
        this(Child.class, forVariable(variable), INITS);
    }

    public QChild(Path<? extends Child> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChild(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChild(PathMetadata metadata, PathInits inits) {
        this(Child.class, metadata, inits);
    }

    public QChild(Class<? extends Child> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aclass = inits.isInitialized("aclass") ? new QAclass(forProperty("aclass"), inits.get("aclass")) : null;
        this.announce = inits.isInitialized("announce") ? new QAnnounce(forProperty("announce"), inits.get("announce")) : null;
        this.parent = inits.isInitialized("parent") ? new QParent(forProperty("parent")) : null;
    }

}

