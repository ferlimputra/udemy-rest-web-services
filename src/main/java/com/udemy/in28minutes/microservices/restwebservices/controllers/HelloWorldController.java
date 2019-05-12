package com.udemy.in28minutes.microservices.restwebservices.controllers;

import com.udemy.in28minutes.microservices.restwebservices.beans.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 */
@RestController
public class HelloWorldController {

  // string hello-world
  @GetMapping("/hello-world")
  public String helloWorld() {
    return "Hello World";
  }

  // bean hello-world with param name
  @GetMapping("/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldBean(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello World, %s", name));
  }

  // bean hello-world
  @GetMapping("/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World");
  }

}
