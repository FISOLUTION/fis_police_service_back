package fis.police.fis_police_server.repository;

import fis.police.fis_police_server.domain.Call;
import fis.police.fis_police_server.dto.CallSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/*
    작성 날짜: 2022/01/10 10:17 오전
    작성자: 고준영
    작성 내용:  call repository interface 기본 메서드(save, findById)
*/
@Repository
public interface CallRepository {

    void save(Call call);
    Call findById(Long id);
}
