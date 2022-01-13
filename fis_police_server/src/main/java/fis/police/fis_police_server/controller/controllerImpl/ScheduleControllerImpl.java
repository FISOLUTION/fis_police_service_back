package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.ScheduleController;
import fis.police.fis_police_server.dto.ScheduleSaveRequest;
import fis.police.fis_police_server.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    작성날짜: 2022/01/12 4:20 PM
    작성자: 이승범
    작성내용: ScheduleControllerImpl 생성
*/
@RestController
@RequiredArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;

    @Override
    @PostMapping("/schedule")// 현장요원 배치
    public Boolean assignAgent(@RequestBody ScheduleSaveRequest request) {
        try{
            return scheduleService.assignAgent(request);
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<Object> selectDate() {
        return null;
    }

    @Override
    public Boolean modifySchedule() {
        return null;
    }

    @Override
    public Boolean announceSchedule() {
        return null;
    }
}
