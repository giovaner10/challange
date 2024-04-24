package br.com.omnilink.desafio.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDetails {

    private String title;
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    private List<String> fields;
}