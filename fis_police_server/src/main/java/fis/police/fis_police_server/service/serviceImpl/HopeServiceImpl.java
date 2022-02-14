package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.Hope;
import fis.police.fis_police_server.domain.Officials;
import fis.police.fis_police_server.dto.HopeSaveRequest;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.repository.HopeRepository;
import fis.police.fis_police_server.repository.OfficialsRepository;
import fis.police.fis_police_server.service.HopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HopeServiceImpl implements HopeService {

    private final HopeRepository hopeRepository;
    private final CenterRepository centerRepository;
    private final OfficialsRepository officialsRepository;


    @Override
    public void saveHope(HopeSaveRequest request, Center center, Officials officials) {
        Hope hope = Hope.createHope(request, officials);
        hopeRepository.saveHope(hope);
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
