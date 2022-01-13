package fis.police.fis_police_server.repository.queryMethod;

import com.querydsl.core.types.dsl.BooleanExpression;
import fis.police.fis_police_server.domain.QCenter;

public class CenterQueryMethod {
    protected BooleanExpression cNameLike(String c_name){
       return c_name == null ? null : QCenter.center.c_name.eq(c_name);
    }

    protected BooleanExpression cPhLike(String c_ph) {
        return c_ph == null ? null : QCenter.center.c_ph.eq(c_ph);
    }

    protected BooleanExpression cAddressLike(String c_address) {
        return c_address == null ? null : QCenter.center.c_address.eq(c_address);
    }
}
