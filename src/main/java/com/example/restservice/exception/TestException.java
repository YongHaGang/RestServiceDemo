package com.example.restservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public TestException(Exception e) {
        super(e);
    }
}
