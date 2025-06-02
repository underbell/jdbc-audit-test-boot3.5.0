package com.example.audit.jdbc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class User implements Persistable<String> {
  @Id private String email;
  private String name;

  public User() {}

  public User(String email, String name) {
    this.email = email;
    this.name = name;
  }

  @CreatedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime createdAt;

  @LastModifiedDate
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime modifiedAt;

  @Override
  public String getId() {
    return this.email;
  }

  @Override
  public boolean isNew() {
    return createdAt == null;
  }
}
