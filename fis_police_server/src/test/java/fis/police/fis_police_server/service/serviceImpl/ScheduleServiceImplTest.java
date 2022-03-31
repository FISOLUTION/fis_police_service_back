package fis.police.fis_police_server.service.serviceImpl;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ScheduleServiceImplTest {
//
//    @Autowired
//    private ScheduleService scheduleService;
//    @Autowired
//    private CenterRepository centerRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AgentRepository agentRepository;
//    @Autowired
//    private ScheduleRepository scheduleRepository;
//
//    @Test
//    public void assignAgent() throws Exception {
//        // given
//        Center center = new Center("센터1", Participation.PARTICIPATION);
//        centerRepository.save(center);
//        User user = new User("12121", "1212", "1212", "1212",
//                LocalDate.now(), UserAuthority.USER);
//        userRepository.save(user);
//        Agent agent = Agent.createAgent("asd", "123", "12312", "분당구 불정로 6", HasCar.CAR,
//                "", LocalDate.now(), 123D, 123D, UserAuthority.AGENT);
//        agentRepository.save(agent);
//        // when
//        ScheduleSaveRequest scheduleSaveRequest = new ScheduleSaveRequest(center.getId(), agent.getId(),
//                LocalDate.now(), LocalDate.now(), LocalTime.now(), 123, "", "");
//        Schedule savedSchedule = scheduleService.assignAgent(scheduleSaveRequest, user.getId());
//        // then
//        Schedule findSchedule = scheduleRepository.findById(savedSchedule.getId());
//        assertThat(savedSchedule).isEqualTo(findSchedule);
//    }
//
//    @Test
//    public void assignAgent_식별되지않는아이디() throws Exception {
//        // given
//        Center center = new Center("센터1", Participation.PARTICIPATION);
//        centerRepository.save(center);
//        User user = new User("12121", "1212", "1212", "1212",
//                LocalDate.now(), UserAuthority.USER);
//        userRepository.save(user);
//        Agent agent = Agent.createAgent("asd", "123", "12312", "분당구 불정로 6", HasCar.CAR,
//                "", LocalDate.now(), 123D, 123D, UserAuthority.AGENT);
//        agentRepository.save(agent);
//        // when
//        ScheduleSaveRequest scheduleSaveRequest = new ScheduleSaveRequest(5123412L, 512342L,
//                LocalDate.now(), LocalDate.now(), LocalTime.now(), 123, "", "");
//        // then
//        assertThatThrownBy(() -> scheduleService.assignAgent(scheduleSaveRequest, user.getId()))
//                .isInstanceOf(NullPointerException.class);
//    }
//
//    @Test
//    public void selectDate() throws Exception {
//        // given
//        Center center = new Center("센터1", Participation.PARTICIPATION);
//        centerRepository.save(center);
//        User user = new User("12121", "1212", "1212", "1212",
//                LocalDate.now(), UserAuthority.USER);
//        userRepository.save(user);
//        Agent agent = Agent.createAgent("asd", "123", "12312", "분당구 불정로 6", HasCar.CAR,
//                "", LocalDate.now(), 123D, 123D, UserAuthority.AGENT);
//        agentRepository.save(agent);
//        ScheduleSaveRequest scheduleSaveRequest = new ScheduleSaveRequest(center.getId(), agent.getId(),
//                LocalDate.now(), LocalDate.now(), LocalTime.now(), 123, "", "");
//        Schedule savedSchedule = scheduleService.assignAgent(scheduleSaveRequest, user.getId());
//        // when
//        List<ScheduleByDateResponse> schedules = scheduleService.selectDate(LocalDate.now());
//        // then
//        assertThat(schedules).extracting("schedule_id").contains(savedSchedule.getId());
//    }
//
//    @Test
//    public void modifySchedule() throws Exception {
//        // given
//        Center center1 = new Center("센터1", Participation.PARTICIPATION);
//        centerRepository.save(center1);
//        Center center2 = new Center("센터2", Participation.PARTICIPATION);
//        centerRepository.save(center2);
//        User user = new User("12121", "1212", "1212", "1212",
//                LocalDate.now(), UserAuthority.USER);
//        userRepository.save(user);
//        Agent agent = Agent.createAgent("asd", "123", "12312", "분당구 불정로 6", HasCar.CAR,
//                "", LocalDate.now(), 123D, 123D, UserAuthority.AGENT);
//        agentRepository.save(agent);
//        ScheduleSaveRequest scheduleSaveRequest = new ScheduleSaveRequest(center1.getId(), agent.getId(),
//                LocalDate.now(), LocalDate.now(), LocalTime.now(), 123, "", "");
//        Schedule savedSchedule = scheduleService.assignAgent(scheduleSaveRequest, user.getId());
//        // when
//        ScheduleModifyRequest request = new ScheduleModifyRequest(savedSchedule.getId(), agent.getA_code(),
//                center2.getId(), savedSchedule.getEstimate_num(), savedSchedule.getVisit_date(), savedSchedule.getVisit_time(),
//                savedSchedule.getCenter_etc(), savedSchedule.getAgent_etc(), savedSchedule.getModified_info(), savedSchedule.getTotal_etc(),
//                savedSchedule.getCall_check(), savedSchedule.getCall_check_info(), false);
//        Schedule modifiedSchedule = scheduleService.modifySchedule(request);
//        // then
//        assertThat(modifiedSchedule).extracting("center").isEqualTo(center2);
//    }
//
//    @Test
//    public void modifySchedule_() throws Exception {
//        // given
//        Center center1 = new Center("센터1", Participation.PARTICIPATION);
//        centerRepository.save(center1);
//        Center center2 = new Center("센터2", Participation.PARTICIPATION);
//        centerRepository.save(center2);
//        User user = new User("12121", "1212", "1212", "1212",
//                LocalDate.now(), UserAuthority.USER);
//        userRepository.save(user);
//        Agent agent = Agent.createAgent("asd", "123", "12312", "분당구 불정로 6", HasCar.CAR,
//                "", LocalDate.now(), 123D, 123D, UserAuthority.AGENT);
//        agentRepository.save(agent);
//        ScheduleSaveRequest scheduleSaveRequest = new ScheduleSaveRequest(center1.getId(), agent.getId(),
//                LocalDate.now(), LocalDate.now(), LocalTime.now(), 123, "", "");
//        Schedule savedSchedule = scheduleService.assignAgent(scheduleSaveRequest, user.getId());
//        // when
//        ScheduleModifyRequest request = new ScheduleModifyRequest(savedSchedule.getId(), "2143123",
//                center2.getId(), savedSchedule.getEstimate_num(), savedSchedule.getVisit_date(), savedSchedule.getVisit_time(),
//                savedSchedule.getCenter_etc(), savedSchedule.getAgent_etc(), savedSchedule.getModified_info(), savedSchedule.getTotal_etc(),
//                savedSchedule.getCall_check(), savedSchedule.getCall_check_info(), false);
//        // then
//        assertThatThrownBy(() -> scheduleService.modifySchedule(request))
//                .isInstanceOf(NullPointerException.class);
//    }
//
//    @Test
//    public void cancelSchedule() throws Exception {
//        // given
//        Center center1 = new Center("센터1", Participation.PARTICIPATION);
//        centerRepository.save(center1);
//        Center center2 = new Center("센터2", Participation.PARTICIPATION);
//        centerRepository.save(center2);
//        User user = new User("12121", "1212", "1212", "1212",
//                LocalDate.now(), UserAuthority.USER);
//        userRepository.save(user);
//        Agent agent = Agent.createAgent("asd", "123", "12312", "분당구 불정로 6", HasCar.CAR,
//                "", LocalDate.now(), 123D, 123D, UserAuthority.AGENT);
//        agentRepository.save(agent);
//        ScheduleSaveRequest scheduleSaveRequest = new ScheduleSaveRequest(center1.getId(), agent.getId(),
//                LocalDate.now(), LocalDate.now(), LocalTime.now(), 123, "", "");
//        Schedule savedSchedule = scheduleService.assignAgent(scheduleSaveRequest, user.getId());
//        // when
//        scheduleService.cancelSchedule(savedSchedule.getId());
//        // then
//        assertThat(savedSchedule.isValid()).isEqualTo(false);
//    }
}