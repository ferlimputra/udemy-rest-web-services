package com.udemy.in28minutes.microservices.restwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.udemy.in28minutes.microservices.restwebservices.beans.UserBean;
import org.springframework.stereotype.Component;

/**
 * UserDao
 */
@Component
public class UserDao {

  private static List<UserBean> users = new ArrayList<>();

  static {
    users.add(new UserBean("Adam", new Date()));
    users.add(new UserBean("Eve", new Date()));
    users.add(new UserBean("Adam", new Date()));
  }

  public List<UserBean> getUsers() {
    return users;
  }

  public UserBean save(UserBean user) {
    users.add(user);
    return user;
  }

  public UserBean getUser(String username) {
    return users.stream().filter(user -> username.equals(user.getUsername())).findFirst()
        .orElse(null);
  }
}
