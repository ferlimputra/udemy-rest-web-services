package com.udemy.in28minutes.microservices.restwebservices.beans;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * User
 */
@ApiModel(description = "Represent a user.")
// @JsonFilter("userFilter")
public class UserBean {

  private UUID id;

  @Size(min = 2, message = "Name must be at least two characters")
  @NotNull(message = "Name cannot be null")
  @ApiModelProperty(example = "John", required = true,
      value = "Name of the user. Name must be at least two characters.")
  private String username;

  @Past(message = "Date of Birth should be in the past")
  @NotNull(message = "Date of Birth cannot be null")
  @ApiModelProperty(value = "Date of Birth should be in the past")
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
