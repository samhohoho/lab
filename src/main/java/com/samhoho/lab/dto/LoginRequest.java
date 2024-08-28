package com.samhoho.lab.dto;

public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
