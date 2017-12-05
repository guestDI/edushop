package com.godeltech.edushop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by d.ihnatovich on 05/12/2017.
 */
@ControllerAdvice
public class BaseController {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRuntimeException(RuntimeException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
