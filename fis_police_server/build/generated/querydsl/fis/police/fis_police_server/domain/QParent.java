package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParent is a Querydsl query type for Parent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParent extends EntityPathBase<Parent> {

    private static final long serialVersionUID = 1788425577L;

    public static final QParent parent = new QParent("parent");

    public final ListPath<Child, QChild> childList = this.<Child, QChild>createList("childList", Child.class, QChild.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath p_nickname = createString("p_nickname");

    public final StringPath p_pwd = createString("p_pwd");

    public final StringPath ph = createString("ph");

    public final EnumPath<fis.police.fis_police_server.domain.enumType.UserAuthority> u_auth = createEnum("u_auth", fis.police.fis_police_server.domain.enumType.UserAuthority.class);

    public QParent(String variable) {
        super(Parent.class, forVariable(variable));
    }

    public QParent(Path<? extends Parent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParent(PathMetadata metadata) {
        super(Parent.class, metadata);
    }

}

