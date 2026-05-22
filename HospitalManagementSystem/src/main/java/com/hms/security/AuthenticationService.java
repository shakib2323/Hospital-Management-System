package com.hms.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hms.dto.LoginRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public String authenticate(LoginRequest request) {
        //Step 1 verify username + password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        //Step 2 load user from DB
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        //Step 3 generate and return token
        return jwtService.generateToken(userDetails);
    }
}