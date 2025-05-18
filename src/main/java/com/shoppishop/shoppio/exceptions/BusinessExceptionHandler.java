package com.shoppishop.shoppio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionResponse> handleException(BusinessException exception) {
    return new ResponseEntity<>(
        new ExceptionResponse(exception.getMessage(), exception.getCode()), HttpStatus.BAD_REQUEST);
  }
}
