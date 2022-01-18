package fis.police.fis_police_server.controller;

public interface AnnounceController {

    /*
    작성날짜: 2022/01/17 2:44 PM
    작성자: 이승범
    작성내용: 카카오톡 보내기위한 인증절차 구현을 위한 메서드 추가 및 일정공지 메서드 수정
*/
    // 일정 공지(카카오톡)
    Boolean announceSchedule();

    // 인증링크 반환 위한 메서드 추가
    String authLink();

    void authCode(String request);

}
