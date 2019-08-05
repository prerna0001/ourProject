package com.stackroute.exceptions;

public class NullIdeaException extends Exception {
    public String message;
    public NullIdeaException() {
    }

public NullIdeaException(String message) {

    super(message);
    this.message=message;
}

    @Override
    public String getMessage() {
        return message;
    }
}

