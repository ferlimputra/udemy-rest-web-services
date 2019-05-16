package com.udemy.in28minutes.microservices.restwebservices.dao;

import java.util.Optional;
import java.util.UUID;
import com.udemy.in28minutes.microservices.restwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 */
@Repository
public interface UserJPADao extends JpaRepository<User, UUID> {

  @Query("SELECT u FROM User u WHERE u.username = :username")
  public Optional<User> findByUsername(@Param("username") String username);

}
