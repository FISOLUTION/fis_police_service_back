package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCheck is a Querydsl query type for Check
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCheck extends EntityPathBase<Check> {

    private static final long serialVersionUID = -923949975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCheck check = new QCheck("check");

    public final QBoard board;

    public final StringPath check_date = createString("check_date");

    public final StringPath check_time = createString("check_time");

    public final QChild child;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCheck(String variable) {
        this(Check.class, forVariable(variable), INITS);
    }

    public QCheck(Path<? extends Check> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCheck(PathMetadata metadata, PathInits inits) {
        this(Check.class, metadata, inits);
    }

    public QCheck(Class<? extends Check> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.child = inits.isInitialized("child") ? new QChild(forProperty("child"), inits.get("child")) : null;
    }

}

