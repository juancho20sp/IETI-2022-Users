package com.planner.users.dto;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class LoginDto {
    private String email;
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
        // this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
