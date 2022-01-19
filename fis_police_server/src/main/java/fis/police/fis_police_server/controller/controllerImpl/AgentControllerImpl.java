package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.controller.AgentController;
import fis.police.fis_police_server.domain.Agent;
import fis.police.fis_police_server.domain.enumType.AgentStatus;
import fis.police.fis_police_server.domain.enumType.HasCar;
import fis.police.fis_police_server.dto.AgentGetResponse;
import fis.police.fis_police_server.dto.AgentModifyRequest;
import fis.police.fis_police_server.dto.AgentSaveRequest;
import fis.police.fis_police_server.dto.AgentGetResult;
import fis.police.fis_police_server.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/*
    작성날짜: 2022/01/11 1:39 PM
    작성자: 이승범
    작성내용: AgentControllerImpl 생성
*/
@RestController
@RequiredArgsConstructor
public class AgentControllerImpl implements AgentController {

    private final AgentService agentService;

    @Override
    @PostMapping("/agent") // 현장요원 추가
    public void saveAgent(@RequestBody AgentSaveRequest request) {
        try{
            agentService.saveAgent(request);
        } catch (IllegalStateException ie){ // 현장요원 코드 중복
            System.out.println("현장요원 코드 중복");
            System.out.println(ie);
        } catch (RestClientException re){ // naver Map api 요청 에러
            System.out.println("api 요청 에러");
            System.out.println(re);
        } catch (ParseException pe){ // naver Map api 파싱 에러(예외처리 필수)
            System.out.println("api 응답 에러");
            System.out.println(pe);
        } catch (IndexOutOfBoundsException oe) { // 잘못된 주소 입력
            System.out.println("잘못된 주소 입력");
            System.out.println(oe);
        } catch (ConstraintViolationException cve){
            System.out.println("요청 데이터가 불완전");
            System.out.println("cve");
        } catch (TransactionSystemException tse){
            System.out.println(tse);
            System.out.println("요청 데이터가 불완전");
        }
    }
    @Override
    @PatchMapping("/agent") // 현장요원 정보 수정
    public void modifyAgent(@RequestBody AgentModifyRequest request) {
        try{
            agentService.modifyAgent(request);
        } catch (IllegalStateException ie){ // 현장요원 코드 중복
            System.out.println("현장요원 코드 중복");
            System.out.println(ie);
        } catch (RestClientException re){ // naver Map api 요청 에러
            System.out.println("api 요청 에러");
            System.out.println(re);
        } catch (ParseException pe){ // naver Map api 파싱 에러(예외처리 필수)
            System.out.println("api 응답 에러");
            System.out.println(pe);
        } catch (IndexOutOfBoundsException oe) { // 잘못된 주소 입력
            System.out.println("잘못된 주소 입력");
            System.out.println(oe);
        }
    }
    @Override
    @GetMapping("/agent") // 전체 현장요원 리스트 조회
    public AgentGetResult getAgent() {
        List<Agent> AllAgentList = agentService.getAgents();
        List<AgentGetResponse> collect = AllAgentList.stream()
                .map(a -> new AgentGetResponse(a.getId(), a.getA_name(), a.getA_ph(), a.getA_code(), a.getA_address(),
                                HasCar.converter(a.getA_hasCar()),a.getA_equipment(),a.getA_receiveDate(),
                                AgentStatus.converter(a.getA_status()))
                        ).collect(Collectors.toList());
        return new AgentGetResult(collect);
    }
}
