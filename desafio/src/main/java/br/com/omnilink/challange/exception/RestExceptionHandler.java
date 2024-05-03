package br.com.omnilink.challange.exception;

import br.com.omnilink.challange.exception.model.BadRequestException;
import br.com.omnilink.challange.exception.model.BadRequestExceptionDetails;
import br.com.omnilink.challange.exception.model.ObjectNotFoundException;
import br.com.omnilink.challange.exception.model.TypetNotFoundException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleObjectNotFoundException(ObjectNotFoundException onfe) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not Found Exception, Check the Documentation")
                        .details(onfe.getMessage())
                        .developerMessage(onfe.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TypetNotFoundException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleTypetNotFoundException(TypetNotFoundException onfe) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Not Found Exception, Check the Documentation")
                        .details(onfe.getMessage())
                        .developerMessage(onfe.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleTypetNotFoundException(UnexpectedTypeException ute) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad fields.")
                        .details(ute.getMessage())
                        .developerMessage(ute.getLocalizedMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }
    //SQLIntegrityConstraintViolationException, ConstraintViolationException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad fields.")
                        .details(errors.toString())
                        .developerMessage(manve.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }



//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
//
//        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
//        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
//
//        return new ResponseEntity<>(
//                ValidationExceptionDetails.builder()
//                        .timestamp(LocalDateTime.now())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .title("Bad Request Exception, Invalid Fields")
//                        .details("Check the field(s) error")
//                        .developerMessage(exception.getClass().getName())
//                        .fields(fields)
//                        .fieldsMessage(fieldsMessage)
//                        .build(), HttpStatus.BAD_REQUEST);
//    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
//                .timestamp(LocalDateTime.now())
//                .status(status.value())
//                .title(ex.getCause().getMessage())
//                .details(ex.getMessage())
//                .developerMessage(ex.getClass().getName())
//                .build();
//
//        return new ResponseEntity<>(exceptionDetails, headers, status);
//    }
}
