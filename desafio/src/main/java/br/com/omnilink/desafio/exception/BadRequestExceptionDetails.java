package br.com.omnilink.desafio.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Builder
public class BadRequestExceptionDetails extends ExceptionDetails {

}