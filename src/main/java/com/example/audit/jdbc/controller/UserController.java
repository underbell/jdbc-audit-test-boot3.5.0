package com.example.audit.jdbc.controller;

import com.example.audit.jdbc.entity.User;
import com.example.audit.jdbc.repository.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/user")
  public User user() {
    return userRepository.save(new User("tester1@test.com", "tester1"));
  }

  @GetMapping("/users")
  public Iterable<User> users() {
    var users =
        List.of(new User("tester2@test.com", "tester2"), new User("tester3@test.com", "tester3"));
    return userRepository.saveAll(users);
  }
}
