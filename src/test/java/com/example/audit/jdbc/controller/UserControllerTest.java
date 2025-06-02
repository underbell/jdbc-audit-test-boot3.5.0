package com.example.audit.jdbc.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.audit.jdbc.ContainerTest;
import com.example.audit.jdbc.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest extends ContainerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void userSave() {
        var user = this.restTemplate.getForObject("http://localhost:" + port + "/user", User.class);
        Assertions.assertNotNull(user);
    }

    @Test
    void userSaveAll() {
        var users = this.restTemplate.getForObject("http://localhost:" + port + "/users", List.class);
        Assertions.assertNotNull(users);
    }
}
