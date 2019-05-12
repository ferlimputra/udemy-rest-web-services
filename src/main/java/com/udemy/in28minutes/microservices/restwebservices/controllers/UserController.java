package com.udemy.in28minutes.microservices.restwebservices.controllers;

import java.net.URI;
import java.util.List;
import com.udemy.in28minutes.microservices.restwebservices.dao.UserDao;
import com.udemy.in28minutes.microservices.restwebservices.beans.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * UserController
 */
@RestController
public class UserController {

  @Autowired
  private UserDao userDao;

  // get all users
  @GetMapping("/users")
  public List<UserBean> getUsers() {
    return userDao.getUsers();
  }

  // get user by name
  @GetMapping("/users/{username}")
  public UserBean getUser(@PathVariable String username) {
    return userDao.getUser(username);
  }

  // save new user
  @PostMapping("/users")
  public ResponseEntity<Object> createUser(@RequestBody UserBean user) {
    UserBean newUser = userDao.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
        .buildAndExpand(newUser.getUsername()).toUri();
    return ResponseEntity.created(location).build();
  }
}
