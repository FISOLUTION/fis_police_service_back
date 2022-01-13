package fis.police.fis_police_server.dto;

import fis.police.fis_police_server.domain.Center;
import fis.police.fis_police_server.domain.enumType.Participation;
import fis.police.fis_police_server.domain.enumType.Visited;

public class SearchNearCenterDTO {
    private Long c_id;
    private String c_address;
    private String c_ph;
    private Participation participation;
    private Visited visited;
    private Float distance;
    private Float c_latitude;    // '위도',
    private Float c_longitude;   // '경도',

    public SearchNearCenterDTO(Center center, Float distance) {
        this.c_id = center.getId();
        this.c_address = center.getC_address();
        this.c_ph = center.getC_ph();
        this.participation = center.getParticipation();
        this.visited = center.getVisited();
        this.c_latitude = center.getC_latitude();
        this.c_longitude = center.getC_longitude();
        this.distance = distance;
    }
}
