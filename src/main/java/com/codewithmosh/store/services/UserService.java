package com.codewithmosh.store.services;


import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@Service
@AllArgsConstructor
//The user loader for authenticaiton
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    //Tells Spring Security HOW to find a user from your database
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow
                (() -> new UsernameNotFoundException("User Not Found"));

        // if user is found we have to return a UserDetails
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }


}

