package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.CenterController;
import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.*;
import fis.police.fis_police_server.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CenterControllerImpl implements CenterController {

    private final CenterService centerService;
    /*
        날짜 : 2022/01/10 3:11 오후
        작성자 : 현승구
        작성내용 :
    */
    @PostMapping
    @Override
    public List<SearchCenterResponseDTO> searchCenter(@RequestParam String c_name, @RequestParam String c_address, @RequestParam String c_ph){
        try {
            SearchCenterDTO searchCenterDTO = new SearchCenterDTO(c_name, c_address, c_ph);
            String name = searchCenterDTO.getC_name();
            String address = searchCenterDTO.getC_address();
            String ph = searchCenterDTO.getC_ph();
            List<SearchCenterResponseDTO> results = centerService.findCenterList(name, address, ph);
            return results;
        } catch (NoResultException noResultException){
            System.out.println(noResultException);
            return new ArrayList<SearchCenterResponseDTO>();
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
    public SelectCenterResponseDTO selectCenter(@RequestParam Long center_id) {
        try{
            Center center =  centerService.centerInfo(center_id);
            return new SelectCenterResponseDTO(center);
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

    @Override
    public Object selectDate(@RequestParam Long center_id, @RequestParam String date) {
        return null;
    }

    @Override
    public Object searchNearCenter(@RequestParam Long center_id, @RequestParam Long range) {
        return null;
    }


    /*
        날짜 : 2022/01/11 8:25 오후
        작성자 : 현승구
        작성내용 :
    */
    @Override
    public void saveCenter(@RequestBody CenterSaveDTO centerSaveDTO) {
        try {
            Center center = CenterSaveDTO.convertToCenter(centerSaveDTO);
            centerService.saveCenter(center);
        } catch (Exception exception) {
            System.out.println(" CenterController.saveCenter 에서 저장 안되는 오류 발생");
            // 오류코드 전성
        }
    }

    /*
        날짜 : 2022/01/11 9:27 오후
        작성자 : 현승구
        작성내용 : 시설 수정
    */
    @Override
    public void modifyCenter(@RequestBody CenterModifyDTO centerModifyDTO) {
        try {
            Center center = CenterModifyDTO.convertToCenter(centerModifyDTO);
            centerService.modifyCenter(center);
        } catch (Exception exception) {
            System.out.println(" CenterController.saveCenter 에서 저장 안되는 오류 발생");
            // 오류코드 전성
        }
    }

    @Override
    public List<Object> getCenter(@RequestParam Long center) {
        return null;
    }

}
