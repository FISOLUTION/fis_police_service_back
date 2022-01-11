package fis.police.fis_police_server.service;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.SearchCenterDTO;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.service.exceptions.CustomSearchException;

import java.util.List;

public interface CenterService {
    // 시설 검색 검색 실패시 null 반환 아무것도 없을 시에 빈 ArrayList 반환 검색결과로 SearchCenterResponseDTO 반환
    List<SearchCenterResponseDTO> findCenterList(SearchCenterDTO searchCenterDTO) throws CustomSearchException;

    //  시설에 해당하는 콜정보, 스케줄정보, 시설정보 반환 로직
    Object centerInfo();

    // 시설 추기
    Boolean saveCenter();

    // 시설 수정
    Boolean modifyCenter();

    // 시설 조회
    List<Center> getCenter();
}
