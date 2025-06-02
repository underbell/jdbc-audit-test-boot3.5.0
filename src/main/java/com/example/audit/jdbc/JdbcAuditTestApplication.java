package com.example.audit.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class JdbcAuditTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(JdbcAuditTestApplication.class, args);
  }
}
