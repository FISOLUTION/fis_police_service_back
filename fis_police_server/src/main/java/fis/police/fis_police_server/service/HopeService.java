package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.HopeSaveRequest;
import fis.police.fis_police_server.dto.Result;

/*
    작성 날짜: 2022/02/14 11:47 오전
    작성자: 고준영
    작성 내용: 신청서 서비스
*/
public interface HopeService {

    void saveHope(HopeSaveRequest request, Center center, Officials officials);
    Result listHope();
    void updateHopeComplete(Long id);
    Officials findOfficials(Long id);
    Center findCenter(Long id);

}