package com.samhoho.lab.service;

import com.samhoho.lab.model.JwtUser;
import com.samhoho.lab.repositories.JwtUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    JwtUserRepository jwtUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JwtUser jwtUser = jwtUserRepository.findUserByEmail(email);
        return new User(jwtUser.getEmail(), jwtUser.getPassword(), new ArrayList<>());
    }
}
