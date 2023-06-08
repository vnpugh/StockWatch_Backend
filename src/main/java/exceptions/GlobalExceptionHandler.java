package exceptions;


import com.stockwatch.capstone.exceptions.InformationExistException;
import com.stockwatch.capstone.exceptions.InformationNotFoundException;
import com.stockwatch.capstone.exceptions.StockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InformationExistException.class, InformationNotFoundException.class, InvalidInputException.class, StockNotFoundException.class, WatchlistAlreadyExistsException.class, WatchListNotFoundException.class, RuntimeException.class})
    public ResponseEntity<Object> handleInformationExistException(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}