package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.controller.controllerImpl.AclassControllerImpl;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = AclassControllerImpl.class)
public class AclassControllerAdvice {

    // 토큰 만료 됐을 때
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public ErrorResult jwtExHandler(JwtException e) {
        log.error("[JwtExHandler] ex", e);
        return new ErrorResult("401", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalAccessException.class)
    public ErrorResult illegalAccessExHandler(IllegalAccessException e) {
        log.error("[illegalExHandler] ex", e);
        return new ErrorResult("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorResult nullExHandler (NullPointerException e) {
        log.error("[nullExHandler] ex", e);
        return new ErrorResult("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult exHandler (Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("500", "ServerError");
    }
}
