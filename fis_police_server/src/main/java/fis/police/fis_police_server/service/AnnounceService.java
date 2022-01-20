package fis.police.fis_police_server.service;

import org.json.simple.parser.ParseException;

public interface AnnounceService {

    // 일정 공지
    void announceSchedule(String accessToken);

    // 카카오한테 accessToken 받아오기
    String getAccessToken(String auth_code) throws ParseException;
}
