package br.com.omnilink.challange.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TypetNotFoundException extends RuntimeException{
    public TypetNotFoundException(String message) {
        super(message);
    }
}
