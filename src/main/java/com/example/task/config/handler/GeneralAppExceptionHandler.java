package com.example.task.config.handler;

import com.example.task.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralAppExceptionHandler extends BaseExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> handleUncaughtException(
      Exception exception){

    String message = exception.getMessage() == null ? "Internal Error" : exception.getMessage();

   final var bodyOfResponse = determineMessage(message, HttpStatus.INTERNAL_SERVER_ERROR);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyOfResponse);
  }
}
