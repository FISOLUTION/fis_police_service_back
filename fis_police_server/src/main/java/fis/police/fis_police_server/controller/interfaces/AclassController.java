package fis.police.fis_police_server.controller.interfaces;

import fis.police.fis_police_server.dto.ClassDataDTO;
import fis.police.fis_police_server.dto.ClassInfoDTO;
import fis.police.fis_police_server.dto.ClassSaveRequest;
import fis.police.fis_police_server.dto.Result;
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
    ClassInfoDTO list(@RequestParam Long class_id, HttpServletRequest request) throws IllegalAccessException;
}
