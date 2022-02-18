package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.controller.controllerImpl.HopeControllerImpl;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = HopeControllerImpl.class)
public class HopeControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResult illegalStateExHandler(IllegalStateException e) {
        log.error("[IllegalStateExHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorResult nullExHandler(NullPointerException e) {
        log.error("[NullPointerExHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[ExceptionHanlder] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
