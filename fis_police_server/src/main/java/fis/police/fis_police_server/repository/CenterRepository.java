package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Center;

//현승구
public interface CenterRepository {

    // 시설 추가
    void save(Center center);

    // 시설 찾기
    Center findById(Long id);

    // 시설 삭제
    void delete(Center center);
}
