package com.backend.demo.exception;

public class InvalidOrdemChegadaException extends RuntimeException {
   public InvalidOrdemChegadaException (String message){
      super(message);
   }

   public InvalidOrdemChegadaException () {
      super("Ordem de chegada procurada nao encontrada.");
   }
}