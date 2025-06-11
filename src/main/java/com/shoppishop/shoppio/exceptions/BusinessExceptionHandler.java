package com.shoppishop.shoppio.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BusinessExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionResponse> handleException(BusinessException exception) {
    log.error(exception.getMessage(), exception);
    return new ResponseEntity<>(
        new ExceptionResponse(exception.getMessage(), exception.getCode()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
    log.error(exception.getMessage(), exception);
    return new ResponseEntity<>(
        new ExceptionResponse(exception.getMessage(), 500L), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
