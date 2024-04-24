package br.com.omnilink.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDetails> handlerNegocioException(ResponseStatusException e, HttpServletRequest httpServletRequest) {

        ExceptionDetails exceptionDetalhes = ExceptionDetails.builder()
                .title("Problema encontrado, verifique a menssagem e o status")
                .message(e.getReason())
                .path(httpServletRequest.getServletPath())
                .timestamp(LocalDateTime.now())
                .status(e.getStatusCode().value()).build();

        return new ResponseEntity<>(exceptionDetalhes, e.getStatusCode());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {

        List<FieldError> todosErros = e.getBindingResult().getFieldErrors();
        List<String> camposComErros = todosErros.stream().map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage()).collect(Collectors.toList());

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Verify fields your request")
                .timestamp(LocalDateTime.now())
                .path(httpServletRequest.getServletPath())
                .fields(camposComErros)
                .status(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {

        ExceptionDetails exceptionDetalhes = ExceptionDetails.builder()
                .title("Verify fields yout request")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .path(httpServletRequest.getServletPath())
                .status(HttpStatus.BAD_REQUEST.value()).build();

        return new ResponseEntity<>(exceptionDetalhes, HttpStatus.BAD_REQUEST);
    }

}