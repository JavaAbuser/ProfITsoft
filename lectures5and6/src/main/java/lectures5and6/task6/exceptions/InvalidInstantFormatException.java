package lectures5and6.task6.exceptions;

public class InvalidInstantFormatException extends Exception{
    public InvalidInstantFormatException() {
        super();
    }

    public InvalidInstantFormatException(String message) {
        super(message);
    }

    public InvalidInstantFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInstantFormatException(Throwable cause) {
        super(cause);
    }

    protected InvalidInstantFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
