package com.planner.users.service;

import com.planner.users.entities.User;
import com.planner.users.service.exceptions.UserServiceException;

import java.util.List;

public interface UserService {
    User create(User user );

    User findById( String id ) throws UserServiceException;

    List<User> getAll();

    void deleteById( String id ) throws UserServiceException;

    User update( User user, String userId ) throws UserServiceException;
}
