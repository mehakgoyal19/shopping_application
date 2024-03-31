package app.exception;

public class OrderAlreadyPaidException extends Exception {
    public OrderAlreadyPaidException(String message) {
        super(message);
    }
}
