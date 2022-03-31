package fis.police.fis_police_server.service.interfaces;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.HopeSaveRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.dto.WellDoneResponse;

/*
    작성 날짜: 2022/02/14 11:47 오전
    작성자: 고준영
    작성 내용: 신청서 서비스
*/
public interface HopeService {

    WellDoneResponse saveHope(HopeSaveRequest request, Center center, Officials officials);
    Result listHope();
    WellDoneResponse updateHopeComplete(Long id);
    Result findHopeStatusByCenter(Center center);
}
