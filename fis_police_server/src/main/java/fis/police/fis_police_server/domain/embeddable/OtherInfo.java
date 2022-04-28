package fis.police.fis_police_server.domain.embeddable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Lob;
import java.time.LocalDate;

@Embeddable
public class OtherInfo {
    private String owner;                   // 대표자명
    private String director;                // 원장명
    private String estDate;                 // 개원일
    private String startTime;               // 운영시작시간
    private String endTime;                 // 운영종료시간
    private Integer minAge;                 // 시설이 관리하는 연령대
    private Integer maxAge;                 //
    private String offerService;            // 제공서비스 (, 로 구분)
    private Integer maxChildCnt;            // 정원
    private LocalDate updateDate;           // 정보 업데이트 일자
    private Boolean signed;                 // 원장의 가입 유무
    private Boolean recruit;                // 원아 모집중
    private Integer waitingNum;             // 원아 모집이 false 일때 대기자 수
    @Lob
    private String introText;               // 시설 소개글
    private Integer imgCnt;                 // 시설 이미지 개수 최대 20장
    private Integer videoCnt;               // 시설 동영상 갯수 최대 5개

    @Embedded
    private ClassInfo classInfo;            // 학급정보
    @Embedded
    private TeacherInfo teacherInfo;        // 선생님 정보
    @Embedded
    private CostInfo costInfo;              // 보육료 정보
    @Embedded
    private BasicInfra basicInfra;          // 기본시설
}
