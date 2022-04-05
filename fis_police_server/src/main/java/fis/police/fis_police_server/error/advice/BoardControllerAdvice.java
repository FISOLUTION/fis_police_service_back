package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.controller.controllerImpl.BoardControllerImpl;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Board 예외처리
 */
@Slf4j
@RestControllerAdvice(assignableTypes = BoardControllerImpl.class)
public class BoardControllerAdvice {
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

    //reqeust param 안썼을 때
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5002");
        error.put("message", ex.getParameterName() + " is missing");
        return ResponseEntity.badRequest().body(error);
    }

    //request param type 다를 때
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
