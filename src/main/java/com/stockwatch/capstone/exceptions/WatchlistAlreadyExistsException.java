package com.stockwatch.capstone.exceptions;


public class WatchlistAlreadyExistsException extends RuntimeException {
    public WatchlistAlreadyExistsException(String message) {
        super(message);
    }
}