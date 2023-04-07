package com.example.task.api.graphql;

import com.example.task.model.User;
import com.example.task.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import javax.sql.DataSource;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureGraphQlTester
public class GraphControllerTest {

  @MockBean
  UserService userService;
  @MockBean
  DataSource dataSource;
  @Autowired
  private GraphQlTester tester;

  @Test
  void getUserTest(){
    doReturn(new User(1L, "Vova", "Surname", "2003-02-15"))
        .when(userService).getUserById(1L);

    String query = "query($id: ID) { getUser(id: $id) { id name } }";
    User user = tester.document(query)
        .variable("id", 1)
        .execute()
        .path("data.getUser")
        .entity(User.class)
        .get();
    Assertions.assertNotNull(user);
    Assertions.assertEquals("Vova", user.getName());
  }
}