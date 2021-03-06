package fis.police.fis_police_server.repository.interfaces;

import fis.police.fis_police_server.domain.Aclass;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.domain.enumType.Accept;

import java.util.List;

/*
    작성 날짜: 2022/02/14 11:45 오전
    작성자: 고준영
    작성 내용: 시설 담당자 저장, 조회
*/
public interface OfficialsRepository {

    void saveOfficials(Officials officials);
    Officials findById(Long id);
    List<Officials> findAll();
    List<Officials> findByNickname(String nickname);
    void acceptOfficial(Long id, Accept accept);
    List<Officials> findOfficialsWaitingAccept(Long center_id, Accept accept);
    void mappingClass(Long official_id, Aclass aclass);
}
