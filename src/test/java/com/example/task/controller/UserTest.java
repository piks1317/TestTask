package com.example.task.controller;

import com.example.task.config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
@Import(TestConfig.class)
public class UserTest {

  @Autowired
  private WebClient webClient;

  @Test
  void testUser() {
    String stringMono = webClient.get().uri("/api/message").retrieve().bodyToMono(String.class).block();
    Assertions.assertEquals("Message: vova you dovdoyob", stringMono.toString());
  }
}
