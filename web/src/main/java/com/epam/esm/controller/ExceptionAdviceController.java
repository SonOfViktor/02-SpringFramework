package com.epam.esm.controller;

import com.epam.esm.entity.ErrorInfo;
import com.epam.esm.exception.ResourceNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionAdviceController {
    @ExceptionHandler
    public ResponseEntity<ErrorInfo> dataNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), 40401);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> typeMismatchExceptionHandler(TypeMismatchException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage(), 40002);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.toString(), 40003);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> noHandlerFoundExceptionHandler (NoHandlerFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), 40004);
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> commonExceptionHandler(Exception exception) {
        ErrorInfo errorInfo = new ErrorInfo(exception.getMessage() + " " + exception.getCause(), 40005);

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
