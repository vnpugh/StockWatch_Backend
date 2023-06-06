package controller;

import models.User;
import models.request.LoginRequest;
import models.request.RegisterUserRequest;
import models.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import service.UserService;


@RestControllerAdvice
@RequestMapping(path = "/api")
public class UserController {
    private UserService userService;

    private UserRepository userRepository;


    /**
     * Sets the UserService instance for the current class.
     *
     * @param userService The UserService instance to be set.
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * POST: endpoint http://localhost:8080/api/auth/users/register
     * Registers a new user.
     *
     * @param userRequest The User object containing the details of the user to be registered.
     * @return The registered User object.
     */
    @PostMapping(path = "/auth/users/register")
    public User registerUser(@RequestBody RegisterUserRequest userRequest) {
        return userService.registerUser(userRequest);
    }


    /**
     * POST: endpoint http://localhost:8080/api/auth/users/login
     * Handles the login request for a user.
     *
     * @param loginRequest The login request object containing user credentials.
     * @return A ResponseEntity representing the HTTP response with the result of the login operation.
     */
    @PostMapping(path = "/auth/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = userService.loginUser(loginRequest);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private boolean isValidCredentials(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * GET: http://localhost:8080/api/users
     * Retrieves the current user.
     *
     * @return The User object representing the current user.
     */
    @GetMapping(path = "/users")
    public User getUser() {
        return userService.getCurrentLoggedInUser();
    }


    /**
     * PUT: http://localhost:8080/api/users
     * Updates the details of the current user.
     *
     * @param updateUserRequest The User object containing the updated details of the user.
     * @return The updated User object representing the current user.
     */
    @PutMapping(path = "/users")
    public User updateCurrentUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateCurrentUser(updateUserRequest);
    }


    /**
     * DELETE: http://localhost:8080/api/users
     * Deletes the current user.
     *
     * @return A ResponseEntity representing the HTTP response with the result of the deletion operation.
     */

    @DeleteMapping(path = "/users")
    public ResponseEntity<?> deleteCurrentUser() {
        return userService.deleteCurrentUser();
    }


}
