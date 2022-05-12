package fis.police.fis_police_server.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * fis.police.fis_police_server.dto.QCalendarListDTO is a Querydsl Projection type for CalendarListDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCalendarListDTO extends ConstructorExpression<CalendarListDTO> {

    private static final long serialVersionUID = -957571703L;

    public QCalendarListDTO(com.querydsl.core.types.Expression<Long> calendar_id, com.querydsl.core.types.Expression<String> date, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> registration_date, com.querydsl.core.types.Expression<String> modify_date, com.querydsl.core.types.Expression<String> delete_date, com.querydsl.core.types.Expression<Long> official_id, com.querydsl.core.types.Expression<String> o_name, com.querydsl.core.types.Expression<String> o_nickname, com.querydsl.core.types.Expression<Long> aclass_id, com.querydsl.core.types.Expression<String> name) {
        super(CalendarListDTO.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, long.class, String.class, String.class, long.class, String.class}, calendar_id, date, title, content, registration_date, modify_date, delete_date, official_id, o_name, o_nickname, aclass_id, name);
    }

}

