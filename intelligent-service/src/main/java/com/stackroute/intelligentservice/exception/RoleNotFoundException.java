package com.stackroute.intelligentservice.exception;

public class RoleNotFoundException extends Exception {
    private String message;

    public RoleNotFoundException() {}

    public RoleNotFoundException(String message)
    {
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
