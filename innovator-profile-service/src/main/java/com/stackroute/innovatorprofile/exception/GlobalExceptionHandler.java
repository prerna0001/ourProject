package com.stackroute.innovatorprofile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailIdAlreadyExistsException.class)
    public ResponseEntity<?> handleUserNameNotFoundException(EmailIdAlreadyExistsException e){
        return new ResponseEntity<String>("Email is already exist !", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailIdNotFoundException.class)
    public ResponseEntity<?> handleUserNameOrPasswordEmptyException(EmailIdNotFoundException e){
        return new ResponseEntity<String>("User Email Id/Password is Empty !", HttpStatus.NOT_FOUND);
    }

}
