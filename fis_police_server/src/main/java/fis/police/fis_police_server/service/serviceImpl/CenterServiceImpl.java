package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
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
    public List<SearchCenterResponseDTO> findCenterList(String c_name, String c_address, String c_ph) throws NoResultException {
       List<SearchCenterResponseDTO> centerList = centerRepository.findBySearchCenterDTO(c_name, c_address, c_ph);
       if(centerList.isEmpty())
           throw new NoResultException("findBSearchCenterDTO  로직에서 발생됨 조건에 맞는 center가 존재하지 않습니다");
       else return centerList;
    }

    @Override
    public Center centerInfo(Long center_id) {
       return centerRepository.findByIdAndFetchAll(center_id);
    }

    @Override
    public void saveCenter(Center center) {
        centerRepository.save(center);
    }

    @Override
    public void modifyCenter(Center center) {
        Center target = centerRepository.findById(center.getId());
        target.modifyCenter(center);
    }

    @Override
    public List<Center> getCenter() {
        return null;
    }

    @Override
    public Center findById(Long id) {
        return centerRepository.findById(id);
    }
}
