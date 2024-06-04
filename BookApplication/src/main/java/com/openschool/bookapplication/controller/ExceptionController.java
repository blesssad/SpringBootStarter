package com.openschool.bookapplication.controller;

import com.openschool.bookapplication.entity.BookException;
import com.openschool.bookapplication.entity.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(BookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionDto handleException(BookException e){
        return new ExceptionDto(e.getMessage());
    }
}
