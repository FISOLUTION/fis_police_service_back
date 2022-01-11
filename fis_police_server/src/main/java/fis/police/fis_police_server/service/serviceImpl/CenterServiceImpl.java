package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.SearchCenterDTO;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.service.CenterService;
import fis.police.fis_police_server.service.exceptions.CustomSearchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service //이걸안써서 오류 내\? 후 조심합시디
@Transactional
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;


    /*
        날짜 : 2022/01/11 11:48 오전
        작성자 : 현승구
        작성내용 : 센터 검색
    */
   @Override
    public List<SearchCenterResponseDTO> findCenterList(SearchCenterDTO searchCenterDTO){
        try {
            String c_name = searchCenterDTO.getC_name();
            String c_address = searchCenterDTO.getC_address();
            String c_ph = searchCenterDTO.getC_ph();
            System.out.println("DTO 초기화 ");
            return centerRepository.findBSearchCenterDTO(c_name, c_address, c_ph);
        } catch (CustomSearchException customSearchException){
            System.out.println("customSearchException = " + customSearchException);
            return new ArrayList<SearchCenterResponseDTO>();
        } catch (NullPointerException nullPointerException){
            System.out.println("nullPointerException 이 발생하였습니다 = " + nullPointerException);
            return null;
        }
    }

    @Override
    public Object centerInfo() {
        return null;
    }

    @Override
    public Boolean saveCenter() {
        return null;
    }

    @Override
    public Boolean modifyCenter() {
        return null;
    }

    @Override
    public List<Center> getCenter() {
        return null;
    }
}
