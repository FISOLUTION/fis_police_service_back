package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CenterController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.CenterService;
import fis.police.fis_police_server.service.exceptions.DuplicateSaveException;
import fis.police.fis_police_server.service.serviceImpl.MapServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CenterControllerImpl implements CenterController {

    private final CenterService centerService;
    private final MapServiceImpl mapService;
    /*
        날짜 : 2022/01/10 3:11 오후
        작성자 : 현승구
        작성내용 :
    */

    private static void logging(HttpServletRequest request, String url, String errorMsg, boolean check) {
        Long userId = (Long) request.getSession().getAttribute("loginUser");
        //true 이면 info false 이면 error log
        if(check){
            log.info("[로그인 id값: {}] [url: {}] [요청: 성공]", userId, url);
        }
        else {
            log.error("[로그인 id값: {}] [url: {}] [에러정보: {}]", userId, url, errorMsg);
        }
    }

    @GetMapping("/center/search")
    @Override
    public Result searchCenter(@RequestParam @Nullable String c_name, @RequestParam @Nullable String c_address, @RequestParam @Nullable String c_ph, HttpServletRequest request){
        try {
            CenterSearchDTO centerSearchDTO = new CenterSearchDTO(c_name, c_address, c_ph);
            String name = centerSearchDTO.getC_name();
            String address = centerSearchDTO.getC_address();
            String ph = centerSearchDTO.getC_ph();
            List<CenterSearchResponseDTO> results = centerService.findCenterList(name, address, ph);
            logging(request, "/center/search", "", true);
            return new Result(results);
        } catch (NoResultException noResultException){
            logging(request, "/center/search", noResultException.getMessage(), false);
            return new Result(new ArrayList<CenterSearchResponseDTO>());
        } catch (NullPointerException nullPointerException){
            logging(request, "/center/search", "null pointer exception 발생", false);
            return null;
        }
    }

    /*
        날짜 : 2022/01/11 8:25 오후
        작성자 : 현승구
        작성내용 : 로직 작성 test코드 아직 작성 안함
    */
    @GetMapping("/center/select")
    @Override
    public Result selectCenter(@RequestParam Long center_id, HttpServletRequest request) {
        try{
            Center center = centerService.centerInfo(center_id);
            List<CenterSearchNearCenterDTO> centerSearchNearCenterDTOList = new ArrayList<CenterSearchNearCenterDTO>();
            mapService.centerNearCenter(center).stream()
                    .forEach(e -> {
                        Double distance = mapService.distance(center.getC_latitude(), center.getC_longitude(), e.getC_latitude(), e.getC_longitude()).doubleValue();
                        centerSearchNearCenterDTOList.add(new CenterSearchNearCenterDTO(e, distance));
                    });
            logging(request, "/center/select?center_id=" + center_id, "", true);
            return new Result(new CenterSelectResponseDTO(center, centerSearchNearCenterDTOList));
        } catch (NoResultException noResultException){
            // 결과물 없을 때 오류코드 발생 -> 해당 시설이 존재 하지 않음
            logging(request, "/center/select?center_id=" + center_id, "결과 없음", false);
            return null;
        } catch (NonUniqueResultException nonUniqueResultException){
            // unigue한 결과 가 아닐때 => 심각한 문제 center_id 값이 여러개
            logging(request, "/center/select?center_id=" + center_id, "여러개의 center: " + center_id + " 가 존재 DB 확인 바람", false);
            return null;
        } catch (Exception exception) {
            logging(request, "/center/select?center_id=" + center_id, "예기치 못한 오류 발생", false);
            return null;
        }
    }

    /*
        날짜 : 2022/01/12 12:06 오후
        작성자 : 현승구
        작성내용 : selectDate 호출시 주변 현장요원 나온다. -> 현장요원들 좌표 던져준다.
    */

    @Override
    @GetMapping("/center/{center_id}/date")
    public Result selectDate(@PathVariable Long center_id, @RequestParam String date, HttpServletRequest request) {
        try {
            Center center = centerService.findById(center_id);
            LocalDate visit_date = LocalDate.parse(date);
            logging(request, "/center/" + center_id + "/date?date=" + date, "", true);
            return new Result(mapService.agentNearCenter(center, 2L).stream()
                    .map(e -> new CenterSelectDateResponseDTO(e, visit_date))
                    .collect(Collectors.toList()));
        } catch (NoResultException noResultException){
            logging(request, "/center/" + center_id + "/date?date=" + date, noResultException.getMessage(), false);
            return null;
        } catch (Exception exception){
            logging(request, "/center/" + center_id + "/date?date=" + date, "예기치 못한 오류 발생", false);
            return null;
        }
    }

    @GetMapping("/center/{center_id}")
    @Override
    public Result searchNearCenter(@PathVariable Long center_id, HttpServletRequest request) {
        try {
            Center center = centerService.findById(center_id);
            List<Center> nearList = mapService.centerNearCenter(center);
            List<CenterSearchNearCenterDTO> centerSearchNearCenterDTOList = new ArrayList<CenterSearchNearCenterDTO>();
            nearList.stream()
                    .forEach(e -> {
                        Double distance = mapService.distance(center.getC_latitude(), center.getC_longitude(), e.getC_latitude(), e.getC_longitude()).doubleValue();
                        centerSearchNearCenterDTOList.add(new CenterSearchNearCenterDTO(e, distance));
                    });
            Long userId = (Long) request.getSession().getAttribute("loginUser");
            log.info("[로그인 id값: {}] [url: {}] [요청: {}]", userId, "/center/" + center_id, "성공");
            return new Result(centerSearchNearCenterDTOList);
        } catch (NoResultException noResultException){
            Long userId = (Long) request.getSession().getAttribute("loginUser");
            log.error("[로그인 id값: {}] [url: {}] [에러정보: {}]", userId, "/center/" + center_id, noResultException.getMessage());
            return null;
        } catch (NullPointerException nullPointerException) {
            Long userId = (Long) request.getSession().getAttribute("loginUser");
            log.error("[로그인 id값: {}] [url: {}] [에러정보: {}]", userId, "/center/" + center_id, "null pointer exception 발생");
            return null;
        } catch (Exception exception){
            Long userId = (Long) request.getSession().getAttribute("loginUser");
            log.error("[로그인 id값: {}] [url: {}] [에러정보: {}]", userId, "/center/" + center_id, "예기치 않은 오류 발생");
            return null;
        }
    }

    /*
        날짜 : 2022/01/11 8:25 오후
        작성자 : 현승구
        작성내용 : 관리자 페이지에서 시설 저장
    */

    @Override
    @PostMapping("/center")
    public void saveCenter(@RequestBody CenterSaveDTO centerSaveDTO, HttpServletResponse response, HttpServletRequest request) {
        try {
            Center center = CenterSaveDTO.convertToCenter(centerSaveDTO);
            centerService.saveCenter(center);
            logging(request, "/center method:post", "", true);
        } catch (DuplicateSaveException duplicateSaveException){
            logging(request, "/center method:post", duplicateSaveException.getMessage(), false);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        } catch (ParseException parseException){
            logging(request, "center method:post", "잘못된 주소 정보 입력됨", false);
        } catch (RestClientException restClientException){
            logging(request, "/center method:post", "naver map api 호출중 오류 발생", false);
        } catch (Exception exception) {
            logging(request, "/center method:post", "예기치 않은 오류 발생", false);
            // 오류코드 전성
        }
    }

    /*
        날짜 : 2022/01/11 9:27 오후
        작성자 : 현승구
        작성내용 : 시설 수정 - 관리자 페이지에서 시설 수정
    */
    @Override
    @PatchMapping("/center")
    public void modifyCenter(@RequestBody CenterModifyDTO centerModifyDTO, HttpServletRequest request) {
        try {
            Center center = CenterModifyDTO.convertToCenter(centerModifyDTO);
            centerService.modifyCenter(center);
        } catch (ParseException parseException){
            logging(request, "center method:post", "잘못된 주소 정보 입력됨", false);
        } catch (RestClientException restClientException){
            logging(request, "/center method:post", "naver map api 호출중 오류 발생", false);
        } catch (Exception exception) {
            logging(request, "/center method:post", "예기치 않은 오류 발생", false);
            // 오류코드 전성
        }
    }


    /*
        날짜 : 2022/01/12 12:05 오후
        작성자 : 현승구
        작성내용 : center_id 를 통해서 검색하는 건데 보류
    */

    @Override
    @GetMapping("/center")
    public List<Object> getCenter(@RequestParam Long center, HttpServletRequest request) {
        return null;
    }



}
