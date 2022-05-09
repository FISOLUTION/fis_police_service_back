package fis.police.fis_police_server.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * fis.police.fis_police_server.dto.QBoardListDTO is a Querydsl Projection type for BoardListDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardListDTO extends ConstructorExpression<BoardListDTO> {

    private static final long serialVersionUID = 622274069L;

    public QBoardListDTO(com.querydsl.core.types.Expression<Long> board_id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> file, com.querydsl.core.types.Expression<String> registration_date, com.querydsl.core.types.Expression<String> registration_time, com.querydsl.core.types.Expression<String> modify_date, com.querydsl.core.types.Expression<String> modify_time, com.querydsl.core.types.Expression<String> delete_date, com.querydsl.core.types.Expression<String> delete_time, com.querydsl.core.types.Expression<Long> official_id, com.querydsl.core.types.Expression<String> o_name, com.querydsl.core.types.Expression<String> o_nickname, com.querydsl.core.types.Expression<Long> aclass_id, com.querydsl.core.types.Expression<String> name) {
        super(BoardListDTO.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, long.class, String.class, String.class, long.class, String.class}, board_id, title, content, file, registration_date, registration_time, modify_date, modify_time, delete_date, delete_time, official_id, o_name, o_nickname, aclass_id, name);
    }

}

