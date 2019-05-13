package com.udemy.in28minutes.microservices.restwebservices.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import com.udemy.in28minutes.microservices.restwebservices.beans.UserBean;
import com.udemy.in28minutes.microservices.restwebservices.dao.UserDao;
import com.udemy.in28minutes.microservices.restwebservices.system.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public Resource<UserBean> getUser(@PathVariable String username) {
    UserBean user = userDao.getUser(username);
    if (user == null) {
      throw new UserNotFoundException(String.format("Username %s does not exists.", username));
    }

    // HATEOAS
    Resource<UserBean> resources = new Resource<>(user);
    ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());
    resources.add(link.withRel("get-users"));
    return resources;
  }

  // delete user by name
  @DeleteMapping("/users/{username}")
  public void deleteUser(@PathVariable String username) {
    boolean result = userDao.deleteUser(username);
    if (!result) {
      throw new UserNotFoundException(String.format("Username %s does not exists.", username));
    }
  }

  // save new user
  @PostMapping("/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody UserBean user) {
    UserBean newUser = userDao.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
        .buildAndExpand(newUser.getUsername()).toUri();
    return ResponseEntity.created(location).build();
  }
}
