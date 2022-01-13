package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;

import java.util.List;

//현승구
public interface CenterRepository {

    // 시설 추가
    void save(Center center);

    // 시설 찾기
    Center findById(Long id);

    // 시설 삭제
    void delete(Center center);

    List<SearchCenterResponseDTO> findBySearchCenterDTO(String c_name, String c_address, String c_ph);

    Center findByIdAndFetchAll(Long center_id);

    List<Center> findNearCenter(double latitude, double longitude);
}
