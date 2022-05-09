package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    작성 날짜: 2022/03/30 5:38 오후
    작성자: 고준영
    작성 내용: 시설 학급
*/
public interface AclassController {
    void save(ClassSaveRequest request, HttpServletRequest httpServletRequest);
    void delete(Long id);
    ClassInfoDTO list(@RequestBody AccessClassDTO classDTO, HttpServletRequest request) throws IllegalAccessException;
}
