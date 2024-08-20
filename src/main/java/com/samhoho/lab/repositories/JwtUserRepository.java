package com.samhoho.lab.repositories;

import com.samhoho.lab.model.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtUserRepository extends JpaRepository<JwtUser, Integer> {
    List<JwtUser> findAll();
    JwtUser findUserByEmail(String email);
}
