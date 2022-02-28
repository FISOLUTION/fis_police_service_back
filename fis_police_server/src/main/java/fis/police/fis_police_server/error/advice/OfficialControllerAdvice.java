package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.controller.OfficialsController;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = OfficialsController.class)
public class OfficialControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResult illegalStateHandler(IllegalStateException e) {
        log.error("[IllegalStateExHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }
}
