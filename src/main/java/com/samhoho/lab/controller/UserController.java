package com.samhoho.lab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @GetMapping("/")
    public String home(Principal principal) {
        return principal.getName();
    }
}
