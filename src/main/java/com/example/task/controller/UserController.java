package com.example.task.controller;

import com.example.task.dto.UserDto;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User userData = userRepository.getUserById(id);
        if(userData == null) {
            return new ResponseEntity<String>("No such user", HttpStatus.BAD_REQUEST);
        }

        UserDto user = new UserDto(userData.getName(), userData.getSurname(),
                userService.getUserAge(userData));
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }
}
