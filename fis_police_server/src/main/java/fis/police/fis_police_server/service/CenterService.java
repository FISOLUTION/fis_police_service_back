package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Center;
import java.util.List;

public interface CenterService {
    // 시설 검색
    List<Center> findCenterList();

    //  시설에 해당하는 콜정보, 스케줄정보, 시설정보 반환 로직
    Object centerInfo();

    // 시설 추기
    Boolean saveCenter();

    // 시설 수정
    Boolean modifyCenter();

    // 시설 조회
    List<Center> getCenter();
}
