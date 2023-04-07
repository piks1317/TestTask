package com.example.task.config.handler;

import com.example.task.model.ErrorMessage;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * Class
 */
@RestControllerAdvice
public class GeneralAppExceptionHandler extends BaseExceptionHandler {

 @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> handleUncaughtException(
      Exception exception){

    String message  = exception.getMessage() == null ? "Internal Error" : exception.getMessage();

    final var bodyOfResponse = determineMessage(message, HttpStatus.INTERNAL_SERVER_ERROR);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyOfResponse);
  }
}
