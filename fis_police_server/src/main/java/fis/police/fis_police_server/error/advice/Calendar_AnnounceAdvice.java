package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.controller.controllerImpl.AnnounceControllerImpl;
import fis.police.fis_police_server.controller.controllerImpl.CalendarControllerImpl;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice(assignableTypes = {CalendarControllerImpl.class, AnnounceControllerImpl.class})
public class Calendar_AnnounceAdvice {
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

    //reqeust param ????????? ???
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5002");
        error.put("message", ex.getParameterName() + " is missing");
        return ResponseEntity.badRequest().body(error);
    }

    //request param type ?????? ???
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        return new ErrorResult("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("500", "ServerError");
    }
}
