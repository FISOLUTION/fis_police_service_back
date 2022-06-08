package fis.police.fis_police_server.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * fis.police.fis_police_server.dto.QAgentByMonthDTO is a Querydsl Projection type for AgentByMonthDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAgentByMonthDTO extends ConstructorExpression<AgentByMonthDTO> {

    private static final long serialVersionUID = 1352086497L;

    public QAgentByMonthDTO(com.querydsl.core.types.Expression<? extends fis.police.fis_police_server.domain.Agent> agent) {
        super(AgentByMonthDTO.class, new Class<?>[]{fis.police.fis_police_server.domain.Agent.class}, agent);
    }

}

