package exceptions;


public class WatchlistAlreadyExistsException extends RuntimeException {
    public WatchlistAlreadyExistsException(String message) {
        super(message);
    }
}
