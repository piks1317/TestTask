package com.example.task.controller;

import com.example.task.TaskApplication;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TaskApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;


    @Test
    public void getUserTest() throws Exception {
        createTestUser(1, "Ivan", "Ivanov", "2003-07-26");
        createTestUser(2, "Vova", "Volodin", "2000-09-30");


        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Vova"));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Ivan"));
    }

    private void createTestUser(long id, String name, String surname, String birthdate) {
        User user = new User(id, name, surname, birthdate);
        repository.saveAndFlush(user);
    }

}
