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
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/*
    작성날짜: 2022/01/11 1:39 PM
    작성자: 이승범
    작성내용: AgentControllerImpl 생성
*/
@RestController
@RequiredArgsConstructor
@Slf4j
public class AgentControllerImpl implements AgentController {

    private final AgentService agentService;

    @Override
    @PostMapping("/agent") // 현장요원 추가
    public void saveAgent(@RequestBody AgentSaveRequest request, HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try{
            agentService.saveAgent(request);
        } catch (IllegalStateException ie){ // 현장요원 코드 중복
            log.warn("[로그인 id값 : {}] [url: {}] [현장요원코드 중복 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", ie.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (RestClientException re){ // naver Map api 요청 에러
            log.warn("[로그인 id값 : {}] [url: {}] [naver Map API 요청 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", re.getMessage());
            response.setStatus(501);
        } catch (ParseException pe){ // naver Map api 파싱 에러(예외처리 필수)
            log.warn("[로그인 id값 : {}] [url: {}] [naver Map API 파싱 에러(예외처리 구현 필수) {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", pe.getMessage());
            response.setStatus(502);
        } catch (IndexOutOfBoundsException oe) { // 잘못된 주소 입력
            log.warn("[로그인 id값 : {}] [url: {}] [잘못된 주소 입력 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", oe.getMessage());
            response.setStatus(403);
        } catch (TransactionSystemException tse){
            log.warn("[로그인 id값 : {}] [url: {}] [요청 데이터 불완전 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", tse.getMessage());
            response.setStatus(402);
        } catch (Exception e){
            log.error("[로그인 id값 : {}] [url:{}] [예상치못한 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", e.getMessage());
        }
    }
    @Override
    @PatchMapping("/agent") // 현장요원 정보 수정
    public void modifyAgent(@RequestBody AgentModifyRequest request, HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try{
            agentService.modifyAgent(request);
        } catch (IllegalStateException ie){ // 현장요원 코드 중복
            log.warn("[로그인 id값 : {}] [url: {}] [현장요원코드 중복 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", ie.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (RestClientException re){ // naver Map api 요청 에러
            log.warn("[로그인 id값 : {}] [url: {}] [naver Map API 요청 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", re.getMessage());
            response.setStatus(501);
        } catch (ParseException pe){ // naver Map api 파싱 에러(예외처리 필수)
            log.warn("[로그인 id값 : {}] [url: {}] [naver Map API 파싱 에러(예외처리 구현 필수) {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", pe.getMessage());
            response.setStatus(502);
        } catch (IndexOutOfBoundsException oe) { // 잘못된 주소 입력
            log.warn("[로그인 id값 : {}] [url: {}] [잘못된 주소 입력 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", oe.getMessage());
            response.setStatus(403);
        } catch (TransactionSystemException tse){
            log.warn("[로그인 id값 : {}] [url: {}] [요청 데이터 불완전 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", tse.getMessage());
            response.setStatus(402);
        } catch (Exception e){
            log.error("[로그인 id값 : {}] [url:{}] [예상치못한 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", e.getMessage());
        }
    }
    @Override
    @GetMapping("/agent") // 전체 현장요원 리스트 조회
    public AgentGetResult getAgent(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        try{
            List<Agent> AllAgentList = agentService.getAgents();
            List<AgentGetResponse> collect = AllAgentList.stream()
                    .map(a -> new AgentGetResponse(a.getId(), a.getA_name(), a.getA_ph(), a.getA_code(), a.getA_address(),
                            HasCar.converter(a.getA_hasCar()),a.getA_equipment(),a.getA_receiveDate(),
                            AgentStatus.converter(a.getA_status()))
                    ).collect(Collectors.toList());
            return new AgentGetResult(collect);
        } catch (Exception e){
            log.error("[로그인 id값 : {}] [url: {}] [예상치못한 에러 {}]",
                    httpServletRequest.getSession().getAttribute("loginUser"), "/agent", e.getMessage());
            response.setStatus(500);
            return null;
        }
    }
}
