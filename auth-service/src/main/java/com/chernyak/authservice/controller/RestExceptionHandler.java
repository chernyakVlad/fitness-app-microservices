package com.chernyak.authservice.controller;

import com.chernyak.authservice.exception.ItemNotFoundException;
import com.chernyak.authservice.exception.UserValidationException;
import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.NoSuchFileException;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserValidationException.class)
    protected ResponseEntity<ExceptionEntity> handleUserValidationException(final UserValidationException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchFileException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(NoSuchFileException e) {
        return new ResponseEntity<>(new ExceptionEntity("File is not found."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(ItemNotFoundException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(BadCredentialsException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(FeignException e) {
        return new ResponseEntity<>(new ExceptionEntity(new String(e.content())), HttpStatus.resolve(e.status()));
    }

    @Data
    @AllArgsConstructor
    static class ExceptionEntity {
        private String message;
    }

    public static String createExceptionMessage(List<ObjectError> errors){
        StringBuilder builder = new StringBuilder();
        errors.forEach((error)->{
            FieldError err = (FieldError) error;
            builder.append("Field - ")
                    .append(err.getField())
                    .append(" : ")
                    .append(err.getDefaultMessage())
                    .append("\n");
        });
        return builder.toString();
    }
}
