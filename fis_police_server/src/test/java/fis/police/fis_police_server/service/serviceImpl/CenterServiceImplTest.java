package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.SearchCenterDTO;
import fis.police.fis_police_server.dto.SearchCenterResponseDTO;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.service.CenterService;
import fis.police.fis_police_server.service.exceptions.CustomSearchException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CenterServiceImplTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private CenterService centerService;

    @Test
    @DisplayName("센터찾기 테스트")
    @Rollback
    void findCenterList() throws CustomSearchException {

        Center center1 = new Center("시설이름 1", "서울", "001");
        Center center2 = new Center("시설이름 2", "서울", "002");
        Center center3 = new Center("시설이름 3", "의정부", "003");
        em.persist(center1);
        em.persist(center2);
        em.persist(center3);

        em.flush();
        em.clear();

        System.out.println("영속성 컨택스트 초기화");

        SearchCenterDTO searchCenterDTO1 = new SearchCenterDTO("시설이름 1", null, null);
        SearchCenterDTO searchCenterDTO2 = new SearchCenterDTO(null, null, null);
        SearchCenterDTO searchCenterDTO3 = new SearchCenterDTO("시설이름 4", "서울", null);
        System.out.println("1차 데이터 검색");
        List<SearchCenterResponseDTO> searchCenterResponseDTOList1 = centerService.findCenterList(searchCenterDTO1);
        searchCenterResponseDTOList1.stream().forEach(e-> System.out.println("e = " + e));
        System.out.println("2차 데이터 검색 다나와야 함");
        List<SearchCenterResponseDTO> searchCenterResponseDTOList2 = centerService.findCenterList(searchCenterDTO2);
        searchCenterResponseDTOList2.stream().forEach(e-> System.out.println("e = " + e));
        System.out.println("3차 데이터 검색");
        List<SearchCenterResponseDTO> searchCenterResponseDTOList3 = centerService.findCenterList(searchCenterDTO3);
        searchCenterResponseDTOList3.stream().forEach(e-> System.out.println("e = " + e));


        Assertions.assertThat(searchCenterResponseDTOList1.size()).isEqualTo(1L);
        Assertions.assertThat(searchCenterResponseDTOList2.size()).isEqualTo(3L);
        Assertions.assertThat(searchCenterResponseDTOList3.size()).isEqualTo(0);
    }

    @Test
    void centerInfo() {
    }

    @Test
    void saveCenter() {
    }

    @Test
    void modifyCenter() {
    }

    @Test
    void getCenter() {
    }
}