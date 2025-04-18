package com.tskifyX.TaskifyX.repository;


import com.tskifyX.TaskifyX.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    static User findUserById(User userId) {

        return userId;
    }

    Optional<User> findByEmail(String email);

}
