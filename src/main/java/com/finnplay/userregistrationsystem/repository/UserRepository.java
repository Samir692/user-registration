package com.finnplay.userregistrationsystem.repository;

import com.finnplay.userregistrationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
