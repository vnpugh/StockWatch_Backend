package com.stockwatch.capstone.service;

import com.stockwatch.capstone.exceptions.InformationExistException;
import com.stockwatch.capstone.exceptions.InformationNotFoundException;
import com.stockwatch.capstone.models.User;
import com.stockwatch.capstone.models.request.LoginRequest;
import com.stockwatch.capstone.models.request.RegisterUserRequest;
import com.stockwatch.capstone.models.request.UpdateUserRequest;
import com.stockwatch.capstone.models.response.LoginResponse;
import com.stockwatch.capstone.repository.UserRepository;
import com.stockwatch.capstone.security.JWTUtils;
import com.stockwatch.capstone.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user to find
     * @return the User object corresponding to the provided email
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * To Login user and generate JWT token.
     *
     * @param loginRequest
     * @return LoginResponse
     */
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword())
        );

        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateJwtToken(user);
        return new LoginResponse(user.getUser().getEmail(), accessToken);
    }


    /**
     * Creates a new user.
     *
     * @param userObject The User object containing the details of the user to be created.
     * @return The created User object.
     * @throws InformationExistException if a user with the same email address already exists.
     */
    public User registerUser(RegisterUserRequest userObject) throws InformationExistException {
        if (!userRepository.existsByEmail(userObject.getEmail())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userObject.getPassword());
            User user = new User();
            user.setEmail(userObject.getEmail());
            user.setPassword(encodedPassword);
            user.setFirstName(userObject.getFirstName());
            return userRepository.save(user);
        } else {
            throw new InformationExistException("User with the given email already exists");
        }
    }

    /**
     * Get current logged-in user for whom jwt is valid.
     *
     * @return User logged-in
     */
    public User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            return userDetails.getUser();
        } else throw new InformationNotFoundException("User not logged in");
    }

    /**
     * Update info of logged-in user
     *
     * @param updateUserRequest
     * @return Updated user
     * @throws InformationNotFoundException
     */
    public User updateCurrentUser(UpdateUserRequest updateUserRequest) throws InformationNotFoundException {
        User updatedUser = getCurrentLoggedInUser();
        if (updatedUser != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
            updatedUser.setEmail(updateUserRequest.getEmail());
            updatedUser.setPassword(encodedPassword);
            return userRepository.save(updatedUser);
        } else {
            throw new InformationNotFoundException("User not logged-in");
        }
    }

    public ResponseEntity<?> deleteCurrentUser() {
        User user = getCurrentLoggedInUser();
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
