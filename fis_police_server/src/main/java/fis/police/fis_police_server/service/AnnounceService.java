package fis.police.fis_police_server.service;

public interface AnnounceService {

    // 일정 공지
    Boolean announceSchedule();

    // 카카오한테 accessToken 받아오기
    String getAccessToken(String auth_code);
}
