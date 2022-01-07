package fis.police.fis_police_server.service;

public interface MessengerService {

    // 메신져 추기
    Boolean saveMessenger();

    // 메신져 삭제(보기완료)
    Boolean deleteMessenger();

    // 메신져 조회
    List<Object> getMessenger();
}
