//package service;
//
//
//
//
//import exceptions.InformationExistException;
//import models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Service;
//import repository.UserRepository;
//
//
//
//@Service
//public class UserService {
//
//    private UserRepository userRepository;
//
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserService(UserRepository userRepository,
//                       @Lazy PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//
//    }
//
//    /**
//     * Creates a new user.
//     * @param userObject The User object containing the details of the user to be created.
//     * @return The created User object.
//     * @throws InformationExistException if a user with the same email address already exists.
//     */
//    public User createUser(User userObject) throws InformationExistException {
//        if (!userRepository.existsByEmailAddress(userObject.getEmail())) {
//            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
//            return userRepository.save(userObject);
//        } else {
//            throw new InformationExistException("User with email address " + userObject.getEmail() +
//                    " already exists.");
//        }
//    }
//
//    /**
//     * Finds a user by their email.
//     * @param email the email of the user to find
//     * @return the User object corresponding to the provided email
//     */
//    public User findUserByEmail(String email){
//        return userRepository.findUserByEmail(email);
//    }
//
//
//
//
//
//
//
//
//
//}
