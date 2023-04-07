package com.example.task.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@TestConfiguration
public class TestConfig {

  @Bean
  public WebClient getWebClient(final WebClient.Builder builder) {
    WebClient webClient = builder
        .baseUrl("http://localhost:8085")
        .build();

    return webClient;
  }
}
