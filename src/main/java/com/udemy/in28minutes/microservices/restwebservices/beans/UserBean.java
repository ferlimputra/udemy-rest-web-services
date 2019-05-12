package com.udemy.in28minutes.microservices.restwebservices.beans;

import java.util.Date;
import java.util.UUID;

/**
 * User
 */
public class UserBean {

  private UUID id;
  private String username;
  private Date dateOfBirth;

  public UserBean(String username, Date dateOfBirth) {
    super();
    this.id = UUID.randomUUID();
    this.username = username;
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the dateOfBirth
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * @param dateOfBirth the dateOfBirth to set
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public String toString() {
    return "User [dateOfBirth=" + dateOfBirth + ", id=" + id + ", username=" + username + "]";
  }
}
