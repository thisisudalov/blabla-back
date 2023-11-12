package org.example.blabla.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private Integer httpCode;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, HttpStatus code) {
        super(message);
        this.httpCode = code.value();
    }

}
