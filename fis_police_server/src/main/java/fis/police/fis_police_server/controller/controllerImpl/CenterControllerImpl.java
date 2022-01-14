package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CenterController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.CenterService;
import fis.police.fis_police_server.service.serviceImpl.MapServiceImpl;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CenterControllerImpl implements CenterController {

    private final CenterService centerService;
    private final MapServiceImpl mapService;
    /*
        날짜 : 2022/01/10 3:11 오후
        작성자 : 현승구
        작성내용 :
    */
    @PostMapping
    @Override
    public Result searchCenter(@RequestParam String c_name, @RequestParam String c_address, @RequestParam String c_ph){
        try {
            SearchCenterDTO searchCenterDTO = new SearchCenterDTO(c_name, c_address, c_ph);
            String name = searchCenterDTO.getC_name();
            String address = searchCenterDTO.getC_address();
            String ph = searchCenterDTO.getC_ph();
            List<SearchCenterResponseDTO> results = centerService.findCenterList(name, address, ph);
            return new Result(results);
        } catch (NoResultException noResultException){
            System.out.println(noResultException);
            return new Result(new ArrayList<SearchCenterResponseDTO>());
        } catch (NullPointerException nullPointerException){
            System.out.println("nullPointerException 이 발생하였습니다 ");
            return null;
        }
    }

    /*
        날짜 : 2022/01/11 8:25 오후
        작성자 : 현승구
        작성내용 : 로직 작성 test코드 아직 작성 안함
    */
    @Override
    public Result selectCenter(@RequestParam Long center_id) {
        try{
            Center center =  centerService.centerInfo(center_id);
            return new Result(new SelectCenterResponseDTO(center));
        } catch (NoResultException noResultException){
            // 결과물 없을 때 오류코드 발생 -> 해당 시설이 존재 하지 않음
            System.out.println("CenterService.centerInfo 에서 발생 해당 시설이 존재 하지 않음" + center_id);
            return null;
        } catch (NonUniqueResultException nonUniqueResultException){
            // unigue한 결과 가 아닐때 => 심각한 문제 center_id 값이 여러개
            System.out.println("CenterService.centerInfo 에서 발생 해당 시설 여러개 존재" + center_id);
            return null;
        } catch (Exception exception) {
            System.out.println("CenterService.centerInfo 예기치 않은 오류 발생" + center_id);
            return null;
        }
    }

    /*
        날짜 : 2022/01/12 12:06 오후
        작성자 : 현승구
        작성내용 : selectDate 호출시 주변 현장요원 나온다. -> 현장요원들 좌표 던져준다.
    */
    @Override
    public Result selectDate(@RequestParam Long center_id, @RequestParam String date) {
//        centerService.
//        mapService.agentNearCenter()
        return null;
    }

    @GetMapping("/center/{center_id}")
    @Override
    public Result searchNearCenter(@PathVariable Long center_id) {
        try {
            Center center = centerService.findById(center_id);
            List<Center> nearList = mapService.centerNearCenter(center);
            List<SearchNearCenterDTO> searchNearCenterDTOList = new ArrayList<SearchNearCenterDTO>();
            nearList.stream()
                    .forEach(e -> {
                        Double distance = mapService.distance(center.getC_latitude(), center.getC_longitude(), e.getC_latitude(), e.getC_longitude()).doubleValue();
                        searchNearCenterDTOList.add(new SearchNearCenterDTO(e, distance));
                    });
            return new Result(searchNearCenterDTOList);
        } catch (NoResultException noResultException){
            System.out.println(noResultException.getMessage());
            return null;
        }
    }


    /*
        날짜 : 2022/01/11 8:25 오후
        작성자 : 현승구
        작성내용 : 관리자 페이지에서 시설 저장
    */
    @Override
    public void saveCenter(@RequestBody CenterSaveDTO centerSaveDTO) {
        try {
            Center center = CenterSaveDTO.convertToCenter(centerSaveDTO);
            centerService.saveCenter(center);
        } catch (ParseException parseException){
            System.out.println(" 잘못된 주소 정보 입력됨 ");
        } catch (RestClientException restClientException){
            System.out.println(" naver map api 호출중 오류 발생 ");
        } catch (Exception exception) {
            System.out.println(" CenterController.saveCenter 에서 저장 안되는 오류 발생");
            // 오류코드 전성
        }
    }

    /*
        날짜 : 2022/01/11 9:27 오후
        작성자 : 현승구
        작성내용 : 시설 수정 - 관리자 페이지에서 시설 수정
    */
    @Override
    public void modifyCenter(@RequestBody CenterModifyDTO centerModifyDTO) {
        try {
            Center center = CenterModifyDTO.convertToCenter(centerModifyDTO);
            centerService.modifyCenter(center);
        } catch (ParseException parseException){
            System.out.println(" 잘못된 주소 정보 입력됨 ");
        } catch (RestClientException restClientException){
            System.out.println(" naver map api 호출중 오류 발생 ");
        } catch (Exception exception) {
            System.out.println(" CenterController.saveCenter 에서 저장 안되는 오류 발생");
            // 오류코드 전성
        }
    }


    /*
        날짜 : 2022/01/12 12:05 오후
        작성자 : 현승구
        작성내용 : center_id 를 통해서 검색하는 건데 보류
    */

    @Override
    public List<Object> getCenter(@RequestParam Long center) {
        return null;
    }



}
