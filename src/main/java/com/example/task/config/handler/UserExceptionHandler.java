package com.example.task.config.handler;

import com.example.task.exception.UserNotFoundException;
import com.example.task.model.ErrorMessage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;


@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(
            @NonNull final HttpServletRequest request,
            @NonNull final Exception exception){

        logger.error("Exception: ", exception);

        ErrorMessage bodyOfResponse = ErrorMessage.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .description(exception.getMessage())
            .timestamp(LocalDate.now())
            .url(request.getRequestURL().toString())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }
}
