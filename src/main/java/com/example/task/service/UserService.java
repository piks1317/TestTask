package com.example.task.service;

import com.example.task.exception.UserNotFoundException;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int getUserAge(User user) {
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(user.getBirthDate());

        return Period.between(birthDate, now).getYears();
    }

    public User getUserById(Long id) {
        User user = userRepository.getUserById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("User with id %s not found", id));
        }

        return user;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
