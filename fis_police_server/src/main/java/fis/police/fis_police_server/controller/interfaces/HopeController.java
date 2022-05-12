package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.HopeSaveRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.dto.WellDoneResponse;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/*
    작성 날짜: 2022/02/14 11:33 오전
    작성자: 고준영
    작성 내용: 신청서 작성(제출), 신청서 조회
*/
public interface HopeController {
    // 신청서 작성
    WellDoneResponse saveHope(HttpServletRequest request, HopeSaveRequest hopeRequest);

    // 신청서 리스트
    Result getHope();

    // 신청서 -> 스케쥴 잡힘  complete 컬럼 업데이트
<<<<<<< HEAD:fis_police_server/src/main/java/fis/police/fis_police_server/controller/HopeController.java
    WellSaveResponse updateComplete(@PathVariable Long hope_id);

=======
    WellDoneResponse updateComplete(@PathVariable Long hope_id);
>>>>>>> f079f65f65d9c5cdac01dc661710bbeeb6e8b80c:fis_police_server/src/main/java/fis/police/fis_police_server/controller/interfaces/HopeController.java
    Result currentSituation(HttpServletRequest request);
}
