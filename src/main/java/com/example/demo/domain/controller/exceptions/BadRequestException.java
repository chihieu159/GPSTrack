package com.example.demo.domain.controller.exceptions;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Exception e) {
        super(e);
    }
}
