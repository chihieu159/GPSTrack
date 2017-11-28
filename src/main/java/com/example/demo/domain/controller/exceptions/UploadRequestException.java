package com.example.demo.domain.controller.exceptions;


public class UploadRequestException extends RuntimeException{
    public UploadRequestException(String message) {
        super(message);
    }

    public UploadRequestException(Exception e) {
        super(e);
    }
}
