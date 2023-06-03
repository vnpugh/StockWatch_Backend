package repository;

import models.User;

public interface UserRepository {
    boolean existsByEmailAddress(String email);

    User save(User userObject);

    User findUserByEmail(String email);
}
