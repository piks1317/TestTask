package com.example.task.api.rest;

import com.example.task.dto.UserDto;
import com.example.task.exception.CustomException;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User userData = userService.getUserById(id);

        UserDto user = new UserDto(userData.getName(), userData.getSurname(),
                userService.getUserAge(userData));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/test/{name}")
    public ResponseEntity<?> getLink(@PathVariable String name) {
        if(name.equals("vova")) {
            throw new CustomException("Vova daun");
        }
        return ResponseEntity.status(HttpStatus.OK).body(String.format("It's OK %s not daun", name));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User userData = userService.addUser(user);

        return ResponseEntity.ok().body(userData);
    }
}
