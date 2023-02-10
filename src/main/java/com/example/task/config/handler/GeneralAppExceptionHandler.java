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

@RestControllerAdvice
public class GeneralAppExceptionHandler extends ResponseEntityExceptionHandler {

 @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> handleUncaughtException(
      Exception exception,
      HttpServletRequest request){

    ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
    HttpStatus status = responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    String message  = status == HttpStatus.INTERNAL_SERVER_ERROR ? "Internal Error" : exception.getMessage();


    ErrorMessage bodyOfResponse = ErrorMessage.builder()
        .status(status.value())
        .description(message)
        .timestamp(LocalDate.now())
        .url(request.getRequestURL().toString())
        .build();

    return ResponseEntity.status(status).body(bodyOfResponse);
  }
}
