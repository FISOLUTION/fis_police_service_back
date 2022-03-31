package fis.police.fis_police_server.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
    작성 날짜: 2022/03/30 5:40 오후
    작성자: 고준영
    작성 내용: 엑셀 -> DB 업로드
*/
public interface ExcelUploadController {
    Object readExcel(@ModelAttribute MultipartFile excelFile) throws IOException, NoSuchMethodException;
}
