package fis.police.fis_police_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T>{
    private T data;
}
