package com.udemy.in28minutes.microservices.restwebservices.controllers;

import java.util.List;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.udemy.in28minutes.microservices.restwebservices.beans.UserBean;
import com.udemy.in28minutes.microservices.restwebservices.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * FilteringController
 */
@RestController
public class FilteringController {

  @Autowired
  private UserDao userDao;

  private SimpleFilterProvider createFilter(String filterName, String... properties) {
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(properties);
    SimpleFilterProvider filters = new SimpleFilterProvider();
    filters.addFilter(filterName, filter);
    return filters;
  }

  private MappingJacksonValue filter(Object bean, FilterProvider filters) {
    MappingJacksonValue mapping = new MappingJacksonValue(bean);
    mapping.setFilters(filters);
    return mapping;
  }

  @GetMapping("/filtered-user/{username}")
  public MappingJacksonValue getFilteredUser(@PathVariable String username) {
    UserBean user = userDao.getUser(username);
    return filter(user, createFilter("userFilter", "id"));
  }

  @GetMapping("/filtered-user")
  public MappingJacksonValue getUsernames() {
    List<UserBean> users = userDao.getUsers();
    return filter(users, createFilter("userFilter", "id", "dateOfBirth"));
  }

}
