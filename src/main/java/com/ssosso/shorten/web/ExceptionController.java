package com.ssosso.shorten.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleBaseException(IllegalArgumentException e){
        return "error/wrong_url";
    }
}
