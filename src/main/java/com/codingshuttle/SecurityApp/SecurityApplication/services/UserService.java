package com.codingshuttle.SecurityApp.SecurityApplication.services;

import com.codingshuttle.SecurityApp.SecurityApplication.dto.LogInDto;
import com.codingshuttle.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.codingshuttle.SecurityApp.SecurityApplication.dto.UserDto;
import com.codingshuttle.SecurityApp.SecurityApplication.entities.UserEntity;
import com.codingshuttle.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.codingshuttle.SecurityApp.SecurityApplication.repositories.Userrepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final Userrepository userRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;



    public UserService(Userrepository userrepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userrepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow( () ->  new ResourceNotFoundException("user is not available with this username " + email));
    }

    public UserDto signUp(SignUpDto signUpDto) {
        Optional<UserEntity> user= userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()){
            throw new  BadCredentialsException("User with this email already exists " + signUpDto.getEmail());
        }
        UserEntity toBeCreatedUser = modelMapper.map(signUpDto, UserEntity.class);

        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        UserEntity savedUser = userRepository.save(toBeCreatedUser);

        return modelMapper.map(savedUser,UserDto.class);



    }


}
