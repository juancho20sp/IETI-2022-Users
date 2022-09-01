package com.planner.users.entities;

import com.planner.users.dto.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String createdAt;

    public User() {
        this.createdAt = java.time.LocalDate.now().toString();
    }

    public User(UserDto userDto) {
        this(userDto.getName(), userDto.getLastName(), userDto.getEmail());
    }

    public User(String name, String lastName, String email){
        this();
        this.name = name;
        this.email = email;
        this.lastName = lastName;

    }

    public User(String id, String name, String lastName, String email, String createdAt) {
        this(name, email, lastName);
        this.id = id;
        this.createdAt = createdAt;
    }

    public void update(User user) {
        name = user.getName();
        lastName = user.getLastName();
        email = user.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
