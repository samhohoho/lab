package com.samhoho.lab.repositories;

import com.samhoho.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtUserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    User findUserByEmail(String email);
}
