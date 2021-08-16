package org.acm.store.controller.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }
}
