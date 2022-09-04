package com.planner.users.repository;

import com.planner.users.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    List<User> findByCreatedAtAfter(String startDate);

    List<User> findByNameLikeOrLastNameLike(String pattern, String secondPattern);
}
