package org.example.blabla.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.blabla.exception.AppException;
import org.example.blabla.model.DefaultResponse;
import org.example.blabla.model.DefaultResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

    @ExceptionHandler(value = {
            AppException.class
    })
    public ResponseEntity<DefaultResponse> handleAppException(AppException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity
                .status(e.getHttpCode() == null ? 500 : e.getHttpCode())
                .body(new DefaultResponse().status(DefaultResponseStatus.ERROR).descr(e.getMessage()));
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    public ResponseEntity<DefaultResponse> handle500(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity
                .status(500)
                .body(new DefaultResponse().status(DefaultResponseStatus.ERROR).descr(e.getMessage()));
    }
}
