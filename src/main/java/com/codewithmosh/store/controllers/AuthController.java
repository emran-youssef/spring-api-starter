package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.User.LoginUserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<?> login(
            @RequestBody @Valid LoginUserRequest request
    ){
        // Receives the request and delegates to Spring Security,
        // It doesn't handle any logic itself — just hands off to AuthenticationManager.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        return ResponseEntity.ok().body(
                Map.of("succeed", "user logged in successfully")
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredintialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}
