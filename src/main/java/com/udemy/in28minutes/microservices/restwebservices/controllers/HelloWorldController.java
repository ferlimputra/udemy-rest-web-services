package com.udemy.in28minutes.microservices.restwebservices.controllers;

import com.udemy.in28minutes.microservices.restwebservices.beans.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 */
@RestController
public class HelloWorldController {

  @Autowired
  private MessageSource messageSource;

  // string hello-world
  @GetMapping("/hello-world")
  public String helloWorld() {
    return "Hello World";
  }

  // internationalized good-morning
  @GetMapping("/good-morning")
  public String goodMorning() {
    return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
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
