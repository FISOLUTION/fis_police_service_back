package fis.police.fis_police_server.service.serviceImpl;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.dto.CenterSearchDTO;
import fis.police.fis_police_server.dto.CenterSearchResponseDTO;
import fis.police.fis_police_server.repository.CenterRepository;
import fis.police.fis_police_server.service.CenterService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

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
    void findCenterList() {

        Center center1 = new Center("시설이름 1", "서울", "001");
        Center center2 = new Center("시설이름 2", "서울", "002");
        Center center3 = new Center("시설이름 3", "의정부", "003");
        em.persist(center1);
        em.persist(center2);
        em.persist(center3);

        em.flush();
        em.clear();

        System.out.println("영속성 컨택스트 초기화");

        CenterSearchDTO centerSearchDTO1 = new CenterSearchDTO("시설이름 1", null, null);
        CenterSearchDTO centerSearchDTO2 = new CenterSearchDTO(null, null, null);
        CenterSearchDTO centerSearchDTO3 = new CenterSearchDTO("시설이름 4", "서울", null);
        System.out.println("1차 데이터 검색");
        List<CenterSearchResponseDTO> centerSearchResponseDTOList1 = centerService.findCenterList("시설이름 1", null, null);
        centerSearchResponseDTOList1.stream().forEach(e-> System.out.println("e = " + e));
        System.out.println("2차 데이터 검색");
        List<CenterSearchResponseDTO> centerSearchResponseDTOList2 = centerService.findCenterList(null, null, null);
        centerSearchResponseDTOList2.stream().forEach(e-> System.out.println("e = " + e));
        List<CenterSearchResponseDTO> centerSearchResponseDTOList3;
        try {
            System.out.println("3차 데이터 검색");
            centerSearchResponseDTOList3 = centerService.findCenterList("시설이름", "서울", null);
            centerSearchResponseDTOList3.stream().forEach(e-> System.out.println("e = " + e));
        } catch (Exception e){
            System.out.println("e = " + e);
        }
        finally {
            //Assertions.assertThat(centerSearchResponseDTOList2.size()).isEqualTo(3L);
            //Assertions.assertThat(centerSearchResponseDTOList1.size()).isEqualTo(1L);
        }
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