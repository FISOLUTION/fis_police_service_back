package fis.police.fis_police_server.error.advice;

import fis.police.fis_police_server.controller.controllerImpl.ExcelUploadControllerImpl;
import fis.police.fis_police_server.error.error_result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice(assignableTypes = ExcelUploadControllerImpl.class)
public class ExcelUploadControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ErrorResult ioExHandler (IOException e) {
        log.error("[ioExHandler] ex", e);
        return new ErrorResult("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult exHandler (Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("500", "ServerError");
    }
}
