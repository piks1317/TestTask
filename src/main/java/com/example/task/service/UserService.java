package com.example.task.service;

import com.example.task.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {

    public int getUserAge(User user) {
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(user.getBirthDate());

        return Period.between(birthDate, now).getYears();
    }

}
