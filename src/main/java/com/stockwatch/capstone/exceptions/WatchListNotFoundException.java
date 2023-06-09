package com.stockwatch.capstone.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WatchListNotFoundException extends RuntimeException {
    public WatchListNotFoundException(String message) {
        super(message);
    }
}