package br.com.omnilink.desafio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationExceptionDetails extends ExceptionDetails {

    private final String fields;
    private final String fieldsMessage;

    public ValidationExceptionDetails(String fields, String fieldsMessage) {
        this.fields = fields;
        this.fieldsMessage = fieldsMessage;
    }
}