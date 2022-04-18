package com.epam.esm.controller;

import com.epam.esm.entity.ErrorInfo;
import com.epam.esm.exception.DataNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler
    public ResponseEntity<ErrorInfo> dataNotFoundExceptionHandler(DataNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), 40401);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> typeMismatchExceptionHandler(TypeMismatchException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), 40001);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> commonExceptionHandler(Exception exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), 40001);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
            //todo code must be based on Status and Object
}
