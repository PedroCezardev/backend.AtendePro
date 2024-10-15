package com.backend.demo.exception;

public class InvalidAgendamentoException extends RuntimeException {
    public InvalidAgendamentoException (String message){
       super(message);
    }
 
    public InvalidAgendamentoException () {
       super("Agendamento procurado nao encontrado.");
    }
}