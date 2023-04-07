package com.example.task.handler;

import com.example.task.config.handler.GeneralAppExceptionHandler;
import com.example.task.model.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class GeneralAppExceptionHandlerTest {

  private final String url = "test-Url";

  @Autowired
  private GeneralAppExceptionHandler generalAppExceptionHandler;

  @Mock
  private MockHttpServletRequest mockHttpServletRequest;


  @Test
  void handleUncaughtExceptions(){
    Exception exception = new Exception();

    when(mockHttpServletRequest.getRequestURL()).thenReturn(new StringBuffer(url));

    ErrorMessage response =
        generalAppExceptionHandler.handleAllUncaughtException(exception)
            .getBody();

    assertNotNull(response);
    assertEquals(500, response.getStatus());
    assertEquals("Internal Error", response.getDescription());
    assertEquals(url, response.getUrl());
  }
}
