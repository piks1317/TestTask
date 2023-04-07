package com.example.task.config.handler;

import com.example.task.model.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class BaseExceptionHandler {

  protected ErrorMessage determineMessage(String message, HttpStatus status) {
    final var bodyOfResponse = ErrorMessage.builder()
        .status(status.value())
        .description(message)
        .build();

    return bodyOfResponse;
  }

}
