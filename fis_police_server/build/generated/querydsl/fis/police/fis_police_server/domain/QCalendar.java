package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendar is a Querydsl query type for Calendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendar extends EntityPathBase<Calendar> {

    private static final long serialVersionUID = -663423939L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendar calendar = new QCalendar("calendar");

    public final QAclass aclass;

    public final StringPath content = createString("content");

    public final StringPath date = createString("date");

    public final StringPath delete_date = createString("delete_date");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath modify_date = createString("modify_date");

    public final QOfficials officials;

    public final StringPath registration_date = createString("registration_date");

    public final StringPath title = createString("title");

    public QCalendar(String variable) {
        this(Calendar.class, forVariable(variable), INITS);
    }

    public QCalendar(Path<? extends Calendar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendar(PathMetadata metadata, PathInits inits) {
        this(Calendar.class, metadata, inits);
    }

    public QCalendar(Class<? extends Calendar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aclass = inits.isInitialized("aclass") ? new QAclass(forProperty("aclass"), inits.get("aclass")) : null;
        this.officials = inits.isInitialized("officials") ? new QOfficials(forProperty("officials"), inits.get("officials")) : null;
    }

}

