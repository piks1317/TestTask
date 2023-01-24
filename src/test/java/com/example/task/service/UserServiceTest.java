package com.example.task.service;

import com.example.task.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {

    UserService userService;

    @Before
    public void setUp(){
        userService = new UserService();
    }

    @Test
    public void testGetUserAge(){
        int result1 = 19;
        int result2 = 24;

        User ivan = new User(1, "Ivan", "Ivanov", "2003-07-26");
        User edna = new User(2, "Edna", "Welch", "1998-10-13");

        Assert.assertEquals(result1, userService.getUserAge(ivan));
        Assert.assertEquals(result2, userService.getUserAge(edna));
    }
}
