package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.AcceptChildDTO;
import fis.police.fis_police_server.dto.ChildModifyRequest;
import fis.police.fis_police_server.dto.ChildSaveRequest;

import javax.servlet.http.HttpServletRequest;
/*
    작성 날짜: 2022/03/30 5:40 오후
    작성자: 고준영
    작성 내용: 아이들 관련
*/
public interface ChildController {

    void save(ChildSaveRequest request, HttpServletRequest httpServletRequest);
    void modify(ChildModifyRequest request, HttpServletRequest httpServletRequest);
    void delete(Long id);
    void acceptChild(AcceptChildDTO childDTO, HttpServletRequest request);
}
