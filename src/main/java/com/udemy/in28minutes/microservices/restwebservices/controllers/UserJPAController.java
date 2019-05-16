package com.udemy.in28minutes.microservices.restwebservices.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import com.udemy.in28minutes.microservices.restwebservices.dao.UserJPADao;
import com.udemy.in28minutes.microservices.restwebservices.model.Comment;
import com.udemy.in28minutes.microservices.restwebservices.model.User;
import com.udemy.in28minutes.microservices.restwebservices.system.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * UserController
 */
@RestController
@RequestMapping("/jpa")
public class UserJPAController {

  @Autowired
  private UserJPADao userJPADao;

  // get all users
  @GetMapping("/users")
  public List<User> getUsers() {
    return userJPADao.findAll();
  }

  @GetMapping(value = "/users", params = "version=2")
  public String getUsersV2() {
    return "param versioning";
  }

  @GetMapping(value = "/users", headers = "X-API-VERSION=2")
  public String getUsersV2Header() {
    return "header param versioning";
  }

  // get user by id
  @GetMapping("/users/id/{id}")
  public Resource<User> getUserById(@PathVariable String id) {
    Optional<User> user = userJPADao.findById(UUID.fromString(id));
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("User with id %s does not exists.", id));
    }

    // HATEOAS
    Resource<User> resources = new Resource<>(user.get());
    ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());
    resources.add(link.withRel("get-users"));
    return resources;
  }

  // get user by name
  @GetMapping(value = "/users/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resource<User> getUser(@PathVariable String username) {
    Optional<User> user = userJPADao.findByUsername(username);
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("Username %s does not exists.", username));
    }

    // HATEOAS
    Resource<User> resources = new Resource<>(user.get());
    ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());
    resources.add(link.withRel("get-users"));
    return resources;
  }

  // get comments by username
  @GetMapping(value = "/users/{username}/comment")
  public List<Comment> getCommentByUsername(@PathVariable String username) {
    Optional<User> user = userJPADao.findByUsername(username);
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("Username %s does not exists.", username));
    }
    return user.get().getComments();
  }

  // delete user by id
  @DeleteMapping("/users/id/{id}")
  public void deleteUserById(@PathVariable String id) {
    userJPADao.deleteById(UUID.fromString(id));
  }

  // delete user by name
  @DeleteMapping("/users/username/{username}")
  public void deleteUserByUsername(@PathVariable String username) {

  }

  // save new user
  @PostMapping("/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    User newUser = userJPADao.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
        .buildAndExpand(newUser.getUsername()).toUri();
    return ResponseEntity.created(location).build();
  }
}
