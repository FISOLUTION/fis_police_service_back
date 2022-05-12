package fis.police.fis_police_server.repository.interfaces;

import fis.police.fis_police_server.domain.Announce;
import fis.police.fis_police_server.dto.AnnounceListDTO;

import java.util.List;

/**
 *    날짜 : 2022/04/06 2:31 오후
 *    작성자 : 원보라
 *    작성내용 : 공지사항
 */
public interface AnnounceRepository {
    void save(Announce announce);

    Announce findById(Long id);

    List<AnnounceListDTO> findAll();
}
