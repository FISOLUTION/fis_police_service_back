package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.controller.RefreshTokenController;
import fis.police.fis_police_server.controller.controllerImpl.RefreshTokenControllerImpl;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = RefreshTokenControllerImpl.class)
public class RefreshTokenControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorResult expiredJwtExHandler(ExpiredJwtException e) {
        log.error("[ExpiredJwtExHandler] ex", e);
        return new ErrorResult("401", "logout plz");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public ErrorResult jwtExHandler(JwtException e) {
        log.error("[JwtExHandler] ex", e);
        return new ErrorResult("401", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("500", "ServerError");
    }
}
