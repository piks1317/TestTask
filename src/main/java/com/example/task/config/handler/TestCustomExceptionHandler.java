package com.example.task.config.handler;

import com.example.task.exception.CustomException;
import com.example.task.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestCustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorMessage> handleCustomExceptionHandler(
      Exception exception,
      HttpServletRequest request){

    ErrorMessage message = ErrorMessage.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .description(exception.getMessage())
        .timestamp(LocalDate.now())
        .url(request.getRequestURL().toString())
        .build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }
}
