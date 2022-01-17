package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Messenger;
import fis.police.fis_police_server.repository.MessengerRepository;
import fis.police.fis_police_server.service.MessengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessengerServiceImpl implements MessengerService {

    private final MessengerRepository messengerRepository;

    @Override
    public void saveMessenger(Messenger messenger) {
        messengerRepository.save(messenger);
    }

    @Override
    public void deleteMessenger(Messenger messenger) {
        messengerRepository.delete(messenger);
    }

    @Override
    public List<Messenger> getMessenger() {
         return messengerRepository.findAll();
    }
}
