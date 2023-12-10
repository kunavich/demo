package com.example.demo.exceptionHandler;

import com.example.demo.exceptionHandler.errors.TransakitonErrorResponse;
import com.example.demo.exceptionHandler.errors.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//TODO ERRORS example error if there no business unit and we entering transaction
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<TransakitonErrorResponse> handleException(TransactionNotFoundException exception) {
        TransakitonErrorResponse error = new TransakitonErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TransakitonErrorResponse> handleException(Exception exception) {
        TransakitonErrorResponse error = new TransakitonErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
