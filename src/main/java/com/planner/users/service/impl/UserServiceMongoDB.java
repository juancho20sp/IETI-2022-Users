package com.planner.users.service.impl;

import com.planner.users.entities.User;
import com.planner.users.repository.UserRepository;
import com.planner.users.service.UserService;
import com.planner.users.service.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMongoDB implements UserService {
    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User findById(String id) throws UserServiceException {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User deleteById(String id) throws UserServiceException {
        if (findById(id) == null) {
            throw new UserServiceException(UserServiceException.USER_NOT_FOUND);
        }

        User deletedUser = findById(id);
        userRepository.deleteById(id);

        return deletedUser;
    }

    @Override
    public User update(User user, String userId) throws UserServiceException {
        User userToUpdate = findById(userId);
        userToUpdate.update(user);

        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersWithNameOrLastNameLike(String queryText) {
        return userRepository.findByNameLikeOrLastNameLike(queryText, queryText);
    }

    @Override
    public List<User> findUsersCreatedAfter(String startDate) {
        return userRepository.findByCreatedAtAfter(startDate);
    }
}
