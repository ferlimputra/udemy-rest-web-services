package com.udemy.in28minutes.microservices.restwebservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.udemy.in28minutes.microservices.restwebservices.beans.UserBean;
import com.udemy.in28minutes.microservices.restwebservices.system.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

/**
 * UserDao
 */
@Service
public class UserService {

  private static List<UserBean> users = new ArrayList<>();

  static {
    users.add(new UserBean("Adam", new Date()));
    users.add(new UserBean("Eve", new Date()));
    users.add(new UserBean("Abel", new Date()));
  }

  public List<UserBean> getUsers() {
    return users;
  }

  public UserBean save(UserBean user) {
    if (getUser(user.getUsername()) != null) {
      throw new UserAlreadyExistsException(String
          .format("User %s already exists. Please choose another username.", user.getUsername()));
    }
    users.add(user);
    return user;
  }

  public UserBean getUser(String username) {
    return users.stream().filter(user -> username.equals(user.getUsername())).findFirst()
        .orElse(null);
  }

  public boolean deleteUser(String username) {
    return users.removeIf(user -> username.equals(user.getUsername()));
  }
}
