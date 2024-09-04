package com.samhoho.lab.repositories;

import com.samhoho.lab.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtUserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findUserByEmail(String email);
}
