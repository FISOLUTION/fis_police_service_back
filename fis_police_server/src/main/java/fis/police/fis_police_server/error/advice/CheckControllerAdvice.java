package fis.police.fis_police_server.error.advice;
import fis.police.fis_police_server.controller.controllerImpl.CheckControllerImpl;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

/**
 * Board 예외처리
 * Check
 */
@Slf4j
@RestControllerAdvice(assignableTypes = CheckControllerImpl.class)
public class CheckControllerAdvice {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public ErrorResult jwtExHandler(JwtException e) {
        log.error("[JwtExHandler] ex", e);
        return new ErrorResult("401", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult IllegalArgumentException (IllegalArgumentException e) {
        log.error("[IllegalArgumentExHandler] ex", e);
        return new ErrorResult("400", e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResult illegalStateExHandler (IllegalStateException e) {
        log.error("[IllegalStateExHandler] ex", e);
        return new ErrorResult("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalCallerException.class)
    public ErrorResult illegalCallerExHandler (IllegalCallerException e) {
        log.error("[IllegalCallerExHandler] ex", e);
        return new ErrorResult("400", e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorResult nullExHandler (NullPointerException e) {
        log.error("[nullExHandler] ex", e);
        return new ErrorResult("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileNotFoundException.class)
    public ErrorResult fileNotFoundException (FileNotFoundException e) {
        log.error("[FileNotFoundException] ex", e);
        return new ErrorResult("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("500", "ServerError");
    }
}
