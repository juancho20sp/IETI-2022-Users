package com.planner.users.entities;

import com.planner.users.dto.UserDto;
import com.planner.users.utils.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String createdAt;
    private String password;
    private List<RoleEnum> roles = new ArrayList<>();


    public User() {
        this.createdAt = java.time.LocalDate.now().toString();
    }

    public User(UserDto userDto) {
        this(userDto.getName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword());
    }

    public User(String name, String lastName, String email, String password){
        this();
        System.out.println("###");
        System.out.println("###");
        System.out.println("###");
        System.out.println("CREANDO USUARIO");
        System.out.println("###");
        System.out.println("###");
        System.out.println("###");
        this.name = name;
        this.email = email;
        this.lastName = lastName;

        hashPassword(password);
    }

    public User(String id, String name, String lastName, String email, String createdAt, String password) {
        this(name, email, lastName, password);
        this.id = id;
        this.createdAt = createdAt;
    }

    public void update(User user) {
        name = user.getName();
        lastName = user.getLastName();
        email = user.getEmail();
        hashPassword(user.getPassword());
    }

    private void hashPassword(String password){
        if (password != null) {
            System.out.println("<<<");
            System.out.println("<<<");
            System.out.println("<<<");
            System.out.println(password);
            System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));

            System.out.println("<<<");
            System.out.println("<<<");
            System.out.println("<<<");
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }
}
