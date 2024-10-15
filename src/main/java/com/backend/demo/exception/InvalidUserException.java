package com.backend.demo.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException (String message){
       super(message);
    }
 
    public InvalidUserException () {
       super("Usuario procurado nao encontrado.");
    }
}