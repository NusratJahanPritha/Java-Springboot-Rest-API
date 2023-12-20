package com.fullstack.application.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("COULD NOT FOUND THE USER WITH ID"+id);
    }

}
