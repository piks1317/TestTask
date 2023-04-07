package com.example.task.handler;

import com.example.task.model.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionHandlerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void givenIllegalUrl_whenHandleNoHandlerFoundException_thenReturn404andResourceNotFound() {
    ResponseEntity<ErrorMessage> response = restTemplate.getForEntity("/", ErrorMessage.class);

    Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    Assertions.assertEquals("Resource not found...", response.getBody().getDescription());
  }

  @Test
  void givenNotValidId_whenHandleUserNotFoundException_thenReturn404andUserNotFoundMessage() {
    ResponseEntity<Map> response
        = restTemplate.getForEntity("/api/{id}", Map.class, 7);

    Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    Assertions.assertEquals("User not found", response.getBody().get("description"));
  }


}
