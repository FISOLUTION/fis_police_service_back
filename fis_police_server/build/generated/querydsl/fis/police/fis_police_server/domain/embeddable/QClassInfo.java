package fis.police.fis_police_server.domain.embeddable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClassInfo is a Querydsl query type for ClassInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QClassInfo extends BeanPath<ClassInfo> {

    private static final long serialVersionUID = -587437476L;

    public static final QClassInfo classInfo = new QClassInfo("classInfo");

    public final NumberPath<Long> child_0 = createNumber("child_0", Long.class);

    public final NumberPath<Long> child_1 = createNumber("child_1", Long.class);

    public final NumberPath<Long> child_2 = createNumber("child_2", Long.class);

    public final NumberPath<Long> child_3 = createNumber("child_3", Long.class);

    public final NumberPath<Long> child_4 = createNumber("child_4", Long.class);

    public final NumberPath<Long> child_5 = createNumber("child_5", Long.class);

    public final NumberPath<Long> child_spe = createNumber("child_spe", Long.class);

    public final NumberPath<Long> class_0 = createNumber("class_0", Long.class);

    public final NumberPath<Long> class_1 = createNumber("class_1", Long.class);

    public final NumberPath<Long> class_2 = createNumber("class_2", Long.class);

    public final NumberPath<Long> class_3 = createNumber("class_3", Long.class);

    public final NumberPath<Long> class_4 = createNumber("class_4", Long.class);

    public final NumberPath<Long> class_5 = createNumber("class_5", Long.class);

    public QClassInfo(String variable) {
        super(ClassInfo.class, forVariable(variable));
    }

    public QClassInfo(Path<? extends ClassInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClassInfo(PathMetadata metadata) {
        super(ClassInfo.class, metadata);
    }

}

