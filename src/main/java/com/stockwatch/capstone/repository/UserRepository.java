package com.stockwatch.capstone.repository;

import com.stockwatch.capstone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findByEmail(String email);

    boolean existsByEmail(String email);


    void delete(User user);


}
