package com.stockwatch.capstone.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Handles various runtime exceptions and returns a ResponseEntity with error details.
 * @param ex the runtime exception
 * @return a ResponseEntity containing the error details
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InformationExistException.class, InformationNotFoundException.class, InvalidInputException.class, StockNotFoundException.class, WatchlistAlreadyExistsException.class, WatchListNotFoundException.class, RuntimeException.class})
    public ResponseEntity<Object> handleException(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    /**
     * Handles MethodArgumentNotValidException and returns a ResponseEntity with error details.
     * @param ex      the MethodArgumentNotValidException
     * @param headers the headers for the response
     * @param status  the HTTP status code for the response
     * @param request the current web request
     * @return a ResponseEntity containing the error details
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
