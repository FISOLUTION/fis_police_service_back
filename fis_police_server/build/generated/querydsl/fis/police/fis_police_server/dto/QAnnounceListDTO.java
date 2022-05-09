package fis.police.fis_police_server.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * fis.police.fis_police_server.dto.QAnnounceListDTO is a Querydsl Projection type for AnnounceListDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAnnounceListDTO extends ConstructorExpression<AnnounceListDTO> {

    private static final long serialVersionUID = 883453438L;

    public QAnnounceListDTO(com.querydsl.core.types.Expression<Long> announce_id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> file, com.querydsl.core.types.Expression<String> registration_date, com.querydsl.core.types.Expression<String> registration_time, com.querydsl.core.types.Expression<String> modify_date, com.querydsl.core.types.Expression<String> modify_time, com.querydsl.core.types.Expression<String> delete_date, com.querydsl.core.types.Expression<String> delete_time, com.querydsl.core.types.Expression<Boolean> external, com.querydsl.core.types.Expression<fis.police.fis_police_server.domain.enumType.AnnounceType> announceType, com.querydsl.core.types.Expression<Long> official_id, com.querydsl.core.types.Expression<String> o_name, com.querydsl.core.types.Expression<String> o_nickname, com.querydsl.core.types.Expression<Long> aclass_id, com.querydsl.core.types.Expression<String> name) {
        super(AnnounceListDTO.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, boolean.class, fis.police.fis_police_server.domain.enumType.AnnounceType.class, long.class, String.class, String.class, long.class, String.class}, announce_id, title, content, file, registration_date, registration_time, modify_date, modify_time, delete_date, delete_time, external, announceType, official_id, o_name, o_nickname, aclass_id, name);
    }

}

