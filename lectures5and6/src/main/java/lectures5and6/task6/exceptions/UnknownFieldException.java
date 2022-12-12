package lectures5and6.task6.exceptions;

public class UnknownFieldException extends RuntimeException{
    public UnknownFieldException() {
        super();
    }

    public UnknownFieldException(String message) {
        super(message);
    }

    public UnknownFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownFieldException(Throwable cause) {
        super(cause);
    }

    protected UnknownFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
