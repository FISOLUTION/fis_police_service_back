package fis.police.fis_police_server.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * fis.police.fis_police_server.dto.QReadBoardList is a Querydsl Projection type for ReadBoardList
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReadBoardList extends ConstructorExpression<ReadBoardList> {

    private static final long serialVersionUID = -1778937996L;

    public QReadBoardList(com.querydsl.core.types.Expression<Long> child_id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> check_date, com.querydsl.core.types.Expression<String> check_time) {
        super(ReadBoardList.class, new Class<?>[]{long.class, String.class, String.class, String.class}, child_id, name, check_date, check_time);
    }

}

