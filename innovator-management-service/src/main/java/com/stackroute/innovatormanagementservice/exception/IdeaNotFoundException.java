package com.stackroute.innovatormanagementservice.exception;

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
