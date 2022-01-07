package fis.police.fis_police_server.controller;

import java.util.List;

//현승구
public interface CenterController {

    // 시설검색
    List<Object> searchCenter();

    // 시설 선택
    Object selectCenter();

    // 날짜 선택 시 주변 현장요원 반환
    Object selectDate();

    // 주변시설 검색
    Object searchNearCenter();

    // 시설 추가
    Boolean saveCenter();

    // 시설 수정
    Boolean modifyCenter();

    // 시설 조회
    List<Object> getCenter();
}
