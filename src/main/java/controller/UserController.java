package controller;

import models.User;
import models.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;
@RestController
@RequestMapping(path = "/api")
public class UserController {
    private UserService userService;


    /**
     * Sets the UserService instance for the current class.
     * @param userService The UserService instance to be set.
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * POST: endpoint http://localhost:8080/api/users/register
     * Registers a new user.
     * @param userObject The User object containing the details of the user to be registered.
     * @return The registered User object.
     */
    @PostMapping(path = "/users/register")
    public User registerUser(@RequestBody User userObject) {
        return userService.registerUser(userObject);
    }



    /**
     * POST: endpoint http://localhost:8080/api/users/login
     * Handles the login request for a user.
     * @param loginRequest The login request object containing user credentials.
     * @return A ResponseEntity representing the HTTP response with the result of the login operation.
     */
    @PostMapping(path="/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest);
    }


    /**
     * GET: http://localhost:8080/api/users
     * Retrieves the current user.
     * @return The User object representing the current user.
     */
    @GetMapping(path = "/users")
    public User getUser() {
        return userService.getCurrentLoggedInUser();
    }


    /**
     * PUT: http://localhost:8080/api/users
     * Updates the details of the current user.
     * @param userObject The User object containing the updated details of the user.
     * @return The updated User object representing the current user.
     */
    @PutMapping(path = "/users")
    public User updateCurrentUser(@RequestBody User userObject) {
        return userService.updateCurrentUser(userObject);
    }


    /**
     * DELETE: http://localhost:8080/api/users
     * Deletes the current user.
     * @return A ResponseEntity representing the HTTP response with the result of the deletion operation.
     */

    @DeleteMapping(path = "/users")
    public ResponseEntity<?> deleteCurrentUser() {
        return userService.deleteCurrentUser();
    }




}
