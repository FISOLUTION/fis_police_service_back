package fis.police.fis_police_server.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAgent is a Querydsl query type for Agent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAgent extends EntityPathBase<Agent> {

    private static final long serialVersionUID = -925826458L;

    public static final QAgent agent = new QAgent("agent");

    public final StringPath a_address = createString("a_address");

    public final StringPath a_code = createString("a_code");

    public final StringPath a_equipment = createString("a_equipment");

    public final EnumPath<fis.police.fis_police_server.domain.enumType.HasCar> a_hasCar = createEnum("a_hasCar", fis.police.fis_police_server.domain.enumType.HasCar.class);

<<<<<<< HEAD
    public final NumberPath<Float> a_latitude = createNumber("a_latitude", Float.class);

    public final NumberPath<Float> a_longitude = createNumber("a_longitude", Float.class);
=======
    public final NumberPath<Double> a_latitude = createNumber("a_latitude", Double.class);

    public final NumberPath<Double> a_longitude = createNumber("a_longitude", Double.class);
>>>>>>> 933cf142a5c157a64523754ffe5be789a718e233

    public final StringPath a_name = createString("a_name");

    public final StringPath a_ph = createString("a_ph");

    public final DateTimePath<java.time.LocalDateTime> a_receiveDate = createDateTime("a_receiveDate", java.time.LocalDateTime.class);

    public final EnumPath<fis.police.fis_police_server.domain.enumType.AgentStatus> a_status = createEnum("a_status", fis.police.fis_police_server.domain.enumType.AgentStatus.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Schedule, QSchedule> scheduleList = this.<Schedule, QSchedule>createList("scheduleList", Schedule.class, QSchedule.class, PathInits.DIRECT2);

    public QAgent(String variable) {
        super(Agent.class, forVariable(variable));
    }

    public QAgent(Path<? extends Agent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgent(PathMetadata metadata) {
        super(Agent.class, metadata);
    }

}

