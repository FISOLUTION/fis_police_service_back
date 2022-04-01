package fis.police.fis_police_server.service;
import fis.police.fis_police_server.domain.Check;
import fis.police.fis_police_server.dto.CheckRequest;

/**
 *    날짜 : 2022/03/31 4:28 오후
 *    작성자 : 원보라
 *    작성내용 : child 가 board 확인
 */
public interface CheckService {
    //확인 버튼
    Check saveCheck(CheckRequest checkRequest);
}
