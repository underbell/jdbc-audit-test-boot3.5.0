package com.example.audit.jdbc.repository;

import com.example.audit.jdbc.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {}
