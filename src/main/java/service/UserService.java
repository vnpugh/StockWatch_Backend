package service;

import exceptions.InformationExistException;
import exceptions.InformationNotFoundException;
import models.User;
import models.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    /**
     * Creates a new user.
     * @param userObject The User object containing the details of the user to be created.
     * @return The created User object.
     * @throws InformationExistException if a user with the same email address already exists.
     */
    public User createUser(User userObject) throws InformationExistException {
        if (!userRepository.existsByEmailAddress(userObject.getEmail())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("User with email address " + userObject.getEmail() +
                    " already exists.");
        }
    }

    /**
     * Finds a user by their email.
     * @param email the email of the user to find
     * @return the User object corresponding to the provided email
     */
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }



    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Validate the username and password
        if (email.equals("email100@gmail.com") && password.equals("password100")) {
            // Successful login
            return ResponseEntity.ok("Login successful");
        } else {
            // Failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    public User registerUser(User userObject) {
        if (!userRepository.existsByEmail(userObject.getEmail())) {
            String encodedPassword = passwordEncoder.encode(userObject.getPassword());
            userObject.setPassword(encodedPassword);
            User registeredUser = userRepository.save(userObject);
            return registeredUser;
        } else {
            throw new RuntimeException("User with the given email already exists");
        }
    }

    public User getCurrentLoggedInUser(){
        Optional<User> user = userRepository.findCurrentLoggedInUserById(getCurrentLoggedInUser().getId());
        if (user.isPresent()) {
            return user.get();
        } else throw new InformationNotFoundException("User with Id " + getCurrentLoggedInUser().getId() + " does not exist.");
    }

    public User updateCurrentUser(User userObject) throws InformationNotFoundException{
        User updatedUser = getCurrentLoggedInUser();
        updatedUser.setEmail(getCurrentLoggedInUser().getEmail());
        updatedUser.setPassword(getCurrentLoggedInUser().getPassword());
        return userRepository.save(updatedUser);
    }

    public ResponseEntity<?> deleteCurrentUser() {
        User user = getCurrentLoggedInUser();
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}