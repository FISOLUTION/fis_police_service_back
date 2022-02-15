package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Hope;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.HopeListResponse;
import fis.police.fis_police_server.dto.HopeSaveRequest;
import fis.police.fis_police_server.dto.Result;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.HopeRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.HopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HopeServiceImpl implements HopeService {

    private final HopeRepository hopeRepository;
    private final CenterRepository centerRepository;
    private final OfficialsRepository officialsRepository;


    @Override
    public void saveHope(HopeSaveRequest request, Center center, Officials officials) {
        Hope hope = Hope.createHope(request, officials, center);
        hopeRepository.saveHope(hope);
    }

    @Override
    public Result listHope() {
        List<Hope> hopes = hopeRepository.listOfHope();
        List<HopeListResponse> collect = hopes.stream()
                .map(hope -> new HopeListResponse(hope.getId(), hope.getCenter().getId(), hope.getOfficials().getId(), hope.getAccept(),
                        hope.getComplete(), hope.getH_date(), hope.getH_time(), hope.getH_mail(), hope.getH_ph()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Officials findOfficials(Long id) {
        return officialsRepository.findById(id);
    }

    @Override
    public Center findCenter(Long id) {
        return centerRepository.findById(id);
    }
}
