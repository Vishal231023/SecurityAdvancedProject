package com.codingshuttle.SecurityApp.SecurityApplication.services;

import com.codingshuttle.SecurityApp.SecurityApplication.dto.LogInDto;
import com.codingshuttle.SecurityApp.SecurityApplication.entities.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String logIn(LogInDto logInDto) {
        Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(logInDto.getEmail(),logInDto.getPassword())
        );
        UserEntity userEntity= (UserEntity)authentication.getPrincipal();

        String token = jwtService.generateToken(userEntity);

        return token;
    }
}
