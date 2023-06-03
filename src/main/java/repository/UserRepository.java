package repository;

import models.User;

public interface UserRepository {
    User save(User user);

    boolean existsByEmailAddress(String email);

    User findUserByEmail(String email);

    boolean existsByEmail(String email);
}
