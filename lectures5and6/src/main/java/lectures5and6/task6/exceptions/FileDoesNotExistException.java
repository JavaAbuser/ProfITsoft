package lectures5and6.task6.exceptions;

public class FileDoesNotExistException extends RuntimeException{
    public FileDoesNotExistException() {
        super();
    }

    public FileDoesNotExistException(String message) {
        super(message);
    }

    public FileDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileDoesNotExistException(Throwable cause) {
        super(cause);
    }

    protected FileDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
