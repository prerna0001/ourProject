package com.stackroute.innovatormanagementservice.exception;

public class EmailIdAlreadyExistsException extends Exception{

        private String message;

        public EmailIdAlreadyExistsException(){}

        public EmailIdAlreadyExistsException(String message)
        {
            super(message);
            this.message=message;
        }


        @Override
        public String getMessage() {
            return message;
        }

    }


