package com.javaabuser.task.exceptions.category;

public class CategoryDoesNotExist extends Exception{
    public CategoryDoesNotExist() {
        super("Category does not exist");
    }

    public CategoryDoesNotExist(String message) {
        super(message);
    }

    public CategoryDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryDoesNotExist(Throwable cause) {
        super(cause);
    }

    protected CategoryDoesNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
