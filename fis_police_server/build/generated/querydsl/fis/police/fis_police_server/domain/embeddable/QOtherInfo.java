package fis.police.fis_police_server.domain.embeddable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOtherInfo is a Querydsl query type for OtherInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOtherInfo extends BeanPath<OtherInfo> {

    private static final long serialVersionUID = 1771353076L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOtherInfo otherInfo = new QOtherInfo("otherInfo");

    public final QBasicInfra basicInfra;

    public final QClassInfo classInfo;

    public final QCostInfo costInfo;

    public final StringPath director = createString("director");

    public final StringPath endTime = createString("endTime");

    public final StringPath estDate = createString("estDate");

    public final NumberPath<Integer> imgCnt = createNumber("imgCnt", Integer.class);

    public final StringPath introText = createString("introText");

    public final NumberPath<Integer> maxAge = createNumber("maxAge", Integer.class);

    public final NumberPath<Integer> maxChildCnt = createNumber("maxChildCnt", Integer.class);

    public final NumberPath<Integer> minAge = createNumber("minAge", Integer.class);

    public final StringPath offerService = createString("offerService");

    public final StringPath owner = createString("owner");

    public final BooleanPath recruit = createBoolean("recruit");

    public final BooleanPath signed = createBoolean("signed");

    public final StringPath startTime = createString("startTime");

    public final QTeacherInfo teacherInfo;

    public final DatePath<java.time.LocalDate> updateDate = createDate("updateDate", java.time.LocalDate.class);

    public final NumberPath<Integer> videoCnt = createNumber("videoCnt", Integer.class);

    public final NumberPath<Integer> waitingNum = createNumber("waitingNum", Integer.class);

    public QOtherInfo(String variable) {
        this(OtherInfo.class, forVariable(variable), INITS);
    }

    public QOtherInfo(Path<? extends OtherInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOtherInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOtherInfo(PathMetadata metadata, PathInits inits) {
        this(OtherInfo.class, metadata, inits);
    }

    public QOtherInfo(Class<? extends OtherInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.basicInfra = inits.isInitialized("basicInfra") ? new QBasicInfra(forProperty("basicInfra")) : null;
        this.classInfo = inits.isInitialized("classInfo") ? new QClassInfo(forProperty("classInfo")) : null;
        this.costInfo = inits.isInitialized("costInfo") ? new QCostInfo(forProperty("costInfo")) : null;
        this.teacherInfo = inits.isInitialized("teacherInfo") ? new QTeacherInfo(forProperty("teacherInfo")) : null;
    }

}

