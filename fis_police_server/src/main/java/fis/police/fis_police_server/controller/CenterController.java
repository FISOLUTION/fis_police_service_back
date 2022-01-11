package fis.police.fis_police_server.controller;

import fis.police.fis_police_server.dto.CenterModifyDTO;
import fis.police.fis_police_server.dto.CenterSaveDTO;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.service.exceptions.CustomSearchException;

import java.util.List;

//현승구
/*
    날짜 : 2022/01/11 3:02 오후
    작성자 : 현승구
    작성내용 : interface 명세
*/
public interface CenterController {

    // 시설검색 서버에서 문제 발생시 오류코드 + null 반환  나머지는 결과 값 전송
    List<SearchCenterResponseDTO> searchCenter(String c_name, String c_address, String c_ph) throws CustomSearchException;

    // 시설 선택
    Object selectCenter(Long center_id);

    // 날짜 선택 시 주변 현장요원 반환
    Object selectDate(Long center_id, String date);

    // 주변시설 검색
    Object searchNearCenter(Long center_id, Long range);

    // 시설 추가
    Boolean saveCenter(CenterSaveDTO centerSaveDTO);

    // 시설 수정
    Boolean modifyCenter(CenterModifyDTO centerModifyDTO);

    // 시설 조회
    List<Object> getCenter(Long center);
}
