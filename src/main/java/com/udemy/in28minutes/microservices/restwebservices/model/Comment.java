package com.udemy.in28minutes.microservices.restwebservices.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

/**
 * Comment
 */
@Entity
@ApiModel(description = "Represent a comment made by a user.")
public class Comment {

  @Id
  @GeneratedValue
  private UUID id;

  @NotNull
  @Size(min = 3, max = 140, message = "Comment must be between 3 to 140 characters.")
  private String content;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;

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
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Comment [content=" + content + ", id=" + id + ", user=" + user + "]";
  }

}
