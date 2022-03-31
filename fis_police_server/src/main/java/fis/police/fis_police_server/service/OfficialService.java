package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.enumType.Accept;
import fis.police.fis_police_server.dto.AcceptOfficialDTO;
import fis.police.fis_police_server.dto.OfficialSaveRequest;
import fis.police.fis_police_server.dto.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    작성 날짜: 2022/02/14 11:48 오전
    작성자: 고준영
    작성 내용: 시설 담당자 서비스
*/
public interface OfficialService {

    void saveOfficials(OfficialSaveRequest request, Center center);
    void modifyOfficials(Officials officialFromRequest, OfficialSaveRequest request, Center center);
    Officials findById(Long id);
    void acceptOfficial(Long official_id, Accept accept);
    Result findOfficialsWaitingAccept(Long center_id);
}
