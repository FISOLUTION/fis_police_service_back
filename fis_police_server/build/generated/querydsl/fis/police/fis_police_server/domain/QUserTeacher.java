package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTeacher is a Querydsl query type for UserTeacher
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTeacher extends EntityPathBase<UserTeacher> {

    private static final long serialVersionUID = -768696264L;

    public static final QUserTeacher userTeacher = new QUserTeacher("userTeacher");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath o_email = createString("o_email");

    public final StringPath o_name = createString("o_name");

    public final StringPath o_nickname = createString("o_nickname");

    public final StringPath o_ph = createString("o_ph");

    public final StringPath o_pwd = createString("o_pwd");

    public QUserTeacher(String variable) {
        super(UserTeacher.class, forVariable(variable));
    }

    public QUserTeacher(Path<? extends UserTeacher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTeacher(PathMetadata metadata) {
        super(UserTeacher.class, metadata);
    }

}

