package com.planner.users.controller;

import com.planner.users.dto.UserDto;
import com.planner.users.entities.User;
import com.planner.users.service.UserService;
import com.planner.users.service.exceptions.UserServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<?> findById( @PathVariable String id ) {
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch ( UserServiceException ex ) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/createdAfter/{date}")
    public ResponseEntity<List<User>> findUsersCreatedAfter(@PathVariable String date) {
        return ResponseEntity.ok(userService.findUsersCreatedAfter(date));
    }

    @GetMapping("/like/{pattern}")
    public ResponseEntity<List<User>> findUsersWithNameOrLastNameLike(@PathVariable String pattern) {
        return ResponseEntity.ok(userService.findUsersWithNameOrLastNameLike(pattern));
    }

    @PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto ) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<?> update( @RequestBody UserDto user, @PathVariable String id ) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            User userMapped = modelMapper.map(user, User.class);

            return new ResponseEntity<>(userService.update(userMapped, id), HttpStatus.OK);
        } catch (UserServiceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> delete( @PathVariable String id ) {
        try {
            return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
        } catch (UserServiceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
