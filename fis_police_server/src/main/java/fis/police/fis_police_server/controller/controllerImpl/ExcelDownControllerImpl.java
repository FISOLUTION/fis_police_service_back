package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ExcelDownController;
import fis.police.fis_police_server.dto.ScheduleByDateResponse;
import fis.police.fis_police_server.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExcelDownControllerImpl implements ExcelDownController {

    private final ScheduleService scheduleService;


    @Override
    @GetMapping("/excel/download")
    public void excelDownload(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, HttpServletResponse response) throws IOException {
        List<ScheduleByDateResponse> list = scheduleService.selectDate(date);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(date+" 스케줄");



        for (int i=0; i<=9; i++){
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)1024);
        }




        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("현장요원");
        cell = row.createCell(1);
        cell.setCellValue("시설이름");
        cell = row.createCell(2);
        cell.setCellValue("시설주소");
        cell = row.createCell(3);
        cell.setCellValue("시설전화번호");
        cell = row.createCell(4);
        cell.setCellValue("방문예정시간");
        cell = row.createCell(5);
        cell.setCellValue("예상인원");
        cell = row.createCell(6);
        cell.setCellValue("수락/거절");
        cell = row.createCell(7);
        cell.setCellValue("특이사항");
        cell = row.createCell(8);
        cell.setCellValue("변경사항");
        cell = row.createCell(9);
        cell.setCellValue("통화이력");


        // 데이터 부분 생성
        for(ScheduleByDateResponse res : list) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(res.getA_name());
            cell = row.createCell(1);
            cell.setCellValue(res.getC_name());
            cell = row.createCell(2);
            cell.setCellValue(res.getC_address());
            cell = row.createCell(3);
            cell.setCellValue(res.getC_ph());
            cell = row.createCell(4);
            cell.setCellValue(String.valueOf(res.getVisit_time()));
            cell = row.createCell(5);
            cell.setCellValue(res.getEstimate_num());
            cell = row.createCell(6);
            cell.setCellValue(String.valueOf(res.getAccept()));
            cell = row.createCell(7);
            cell.setCellValue(res.getTotal_etc());
            cell = row.createCell(8);
            cell.setCellValue(res.getModified_info());
            cell = row.createCell(9);
            cell.setCellValue(res.getCall_check());
        }



        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        //response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}
