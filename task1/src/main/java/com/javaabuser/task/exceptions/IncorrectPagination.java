package com.javaabuser.task.exceptions;

public class IncorrectPagination extends RuntimeException{
    public IncorrectPagination() {
        super("Incorrect pagination");
    }

    public IncorrectPagination(String message) {
        super(message);
    }

    public IncorrectPagination(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPagination(Throwable cause) {
        super(cause);
    }

    protected IncorrectPagination(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
