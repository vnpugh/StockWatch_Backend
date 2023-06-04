package repository;


import models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    boolean existsByEmailAddress(String email);

    User findUserByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findCurrentLoggedInUserById(Long id);

    void delete(User user);
}
