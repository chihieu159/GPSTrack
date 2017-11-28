package com.example.demo.domain.controller.advice;

import com.example.demo.domain.controller.exceptions.BadRequestException;
import com.example.demo.domain.controller.impl.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("An unhandled exception has occured", e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity opBadRequestErrorHandler(BadRequestException e) {
        logger.error("BadRequestException", e);

        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
