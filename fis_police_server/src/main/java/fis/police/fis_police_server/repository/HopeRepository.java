package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Hope;

/*
    작성 날짜: 2022/02/14 11:45 오전
    작성자: 고준영
    작성 내용: 지문등록 신청서 저장, 조회
*/
public interface HopeRepository {
    void saveHope(Hope hope);
    void listOfHope();
}
