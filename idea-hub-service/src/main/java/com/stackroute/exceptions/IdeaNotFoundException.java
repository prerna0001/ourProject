package com.stackroute.exceptions;

public class IdeaNotFoundException extends Exception {
    private String message;

    public IdeaNotFoundException(){}

    public IdeaNotFoundException(String message)
    {
        super(message);
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
