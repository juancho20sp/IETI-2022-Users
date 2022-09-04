package com.planner.users.dto;

public class UserDto {
    private String name;
    private String email;
    private String lastName;
    private String password;

    public UserDto() {
    }

    public UserDto(String name, String email, String lastName, String password) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
