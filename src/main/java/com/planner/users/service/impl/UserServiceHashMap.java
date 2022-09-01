package com.planner.users.service.impl;

import com.planner.users.entities.User;
import com.planner.users.service.UserService;
import com.planner.users.service.exceptions.UserServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// @Service
public class UserServiceHashMap implements UserService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    @Override
    public User create(User user) {
        users.putIfAbsent(user.getId(), user);
        return user;
    }

    @Override
    public User findById(String id) throws UserServiceException {
        Optional<User> optionalUser = Optional.ofNullable(users.get(id));
        optionalUser.orElseThrow(() -> new UserServiceException(UserServiceException.USER_NOT_FOUND));

        return optionalUser.get();
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();

        for (String key : users.keySet()) {
            allUsers.add(users.get(key));
        }

        return allUsers;
    }

    @Override
    public User deleteById(String id) throws UserServiceException {
        if (!users.containsKey(id)) {
            throw new UserServiceException(UserServiceException.USER_NOT_FOUND);
        }

        return users.remove(id);
    }

    @Override
    public User update(User user, String userId) throws UserServiceException {
        if (!users.containsKey(userId)) {
            throw new UserServiceException(UserServiceException.USER_NOT_FOUND);
        }

        users.replace(userId, user);
        return users.get(userId);
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        return null;
    }

    @Override
    public List<User> findUsersCreatedAfter(String startDate) {
        return null;
    }
}
