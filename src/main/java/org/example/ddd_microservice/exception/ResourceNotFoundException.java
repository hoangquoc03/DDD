package org.example.ddd_microservice.exception;

public class ResourceNotFoundException
        extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

}