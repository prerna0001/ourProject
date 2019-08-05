package com.stackroute.exceptions;

public class IdeaAlreadyExistsException extends Exception{
    public String message;

    public IdeaAlreadyExistsException() {
    }

    public IdeaAlreadyExistsException(String message) {
        super(message);
        this.message=message;

    }
    @Override
   public String getMessage()
    {
       return message;
    }
}
