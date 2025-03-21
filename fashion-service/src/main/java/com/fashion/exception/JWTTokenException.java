package com.fashion.exception;

public class JWTTokenException  extends RuntimeException{
    String message;
    public JWTTokenException(String message){
        super(message);
        this.message = message;
    }
}
