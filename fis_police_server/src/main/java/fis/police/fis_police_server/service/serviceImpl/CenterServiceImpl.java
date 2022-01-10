package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.SearchCenterDTO;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.service.CenterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //이걸안써서 오류 내\? 후 조심합시디
public class CenterServiceImpl implements CenterService {
    @Override
    public List<SearchCenterResponseDTO> findCenterList(SearchCenterDTO searchCenterDTO) {
        // 시설 이름, 주소, 전화번호로 검색한다.
        return null; //안돌아가서 써놨음
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
