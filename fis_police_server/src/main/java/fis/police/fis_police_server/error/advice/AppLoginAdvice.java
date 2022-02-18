package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.error.error_result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AppLoginAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorResult nullExHandler(NullPointerException e) {
        log.error("[NullPointerExHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }
}
