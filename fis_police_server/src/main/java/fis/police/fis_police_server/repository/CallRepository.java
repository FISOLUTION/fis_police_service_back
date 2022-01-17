package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Call;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

/*
    작성 날짜: 2022/01/10 10:17 오전
    작성자: 고준영
    작성 내용:  call repository interface 기본 메서드(save, findById)
*/
public interface CallRepository {

    void save(Call call);
    Call findById(Long id);
    /*
        작성 날짜: 2022/01/10 2:17 오후
        작성자: 고준영
        작성 내용:  해당 날짜의 콜 기록을 모두 긁어오는 함수, 서비스에서 콜 직원별로 분류. 따라서 추후에 param으로 date 삽입해야함
    */
    List<Call> testDate(String date);
    List<Call> callByCenter(Long id);
}
