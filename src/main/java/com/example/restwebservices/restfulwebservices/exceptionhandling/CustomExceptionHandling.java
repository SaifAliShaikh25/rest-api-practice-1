package com.example.restwebservices.restfulwebservices.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception exp, WebRequest webRequest) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timeStamp(LocalDateTime.now())
                .message(exp.getMessage())
                .description(webRequest.getDescription(false)).build();
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception exp, WebRequest webRequest) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timeStamp(LocalDateTime.now())
                .message(exp.getMessage())
                .description(webRequest.getDescription(false)).build();
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders httpHeaders, HttpStatusCode httpStatusCode, WebRequest webRequest) {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .timeStamp(LocalDateTime.now())
                // .message(methodArgumentNotValidException.getMessage())
                .message(methodArgumentNotValidException.getFieldError().getDefaultMessage())
                .description(webRequest.getDescription(false)).build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
