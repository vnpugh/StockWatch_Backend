package com.stockwatch.capstone.security;

import com.stockwatch.capstone.models.User;
import com.stockwatch.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    /**
     * UserDetailsService requires one read-only method, which finds a User by the unique email and stores in MyUserDetails
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by the given email and returns UserDetails.
     *
     * @param email the email of the user to load
     * @return the UserDetails of the loaded user
     * @throws UsernameNotFoundException if the user with the given email is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new MyUserDetails(user);
    }
}
