package com.andersonzero0.apipostup.infra;

import com.andersonzero0.apipostup.exceptions.NotFoundException;
import com.andersonzero0.apipostup.exceptions.UserAlreadyExistsException;
import com.andersonzero0.apipostup.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {



//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(
                HttpStatus.BAD_REQUEST,
                "Required request body is missing or malformed",
                null);

        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessage> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        RestErrorMessage threatResponse = new RestErrorMessage(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                errors);

        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleUserNotFound(UserNotFoundException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null);

        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleNotFound(NotFoundException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                null);

        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<RestErrorMessage> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                null);

        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestErrorMessage> handleAuthenticationException(AuthenticationException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage(),
                null);

        return new ResponseEntity<>(threatResponse, new HttpHeaders(), threatResponse.getStatus());
    }

}
