package com.samhoho.lab.controller;

import com.samhoho.lab.auth.TokenProvider;
import com.samhoho.lab.dto.ApiResponse;
import com.samhoho.lab.dto.AuthenticationResponse;
import com.samhoho.lab.dto.LoginRequest;
import com.samhoho.lab.dto.SignUpRequest;
import com.samhoho.lab.model.User;
import com.samhoho.lab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService customUserDetailsService;
    @Autowired
    TokenProvider tokenProvider;

    @PostMapping("/api/auth/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = tokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        Optional<User> user = userRepository.findByEmail(signUpRequest.getEmail());
        if (user.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "wtf Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(newUser);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
