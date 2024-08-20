package com.samhoho.lab.controller;

import com.samhoho.lab.dto.ApiResponse;
import com.samhoho.lab.dto.SignUpRequest;
import com.samhoho.lab.model.JwtUser;
import com.samhoho.lab.repositories.JwtUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    JwtUserRepository jwtUserRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (jwtUserRepository.findUserByEmail(signUpRequest.getEmail()) != null) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        JwtUser jwtUser = new JwtUser(signUpRequest.getEmail(), signUpRequest.getPassword());
        jwtUserRepository.save(jwtUser);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
