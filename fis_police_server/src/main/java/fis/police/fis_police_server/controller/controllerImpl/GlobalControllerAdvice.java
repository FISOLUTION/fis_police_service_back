package fis.police.fis_police_server.controller.controllerImpl;

import fis.police.fis_police_server.error.error_result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.internet.AddressException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(AddressException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult illegalExHandler(AddressException e) {
        return new ErrorResult("BAD", e.getMessage()); }

}
