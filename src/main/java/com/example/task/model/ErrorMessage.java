package com.example.task.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessage {

    int status;
    String description;
    LocalDate timestamp;
    String url;
}
