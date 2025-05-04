package com.gtr3base.UserService.service;

import com.gtr3base.UserService.dto.UserDTO;
import com.gtr3base.UserService.enums.RoleEnum;
import com.gtr3base.UserService.model.Follower;
import com.gtr3base.UserService.model.User;
import com.gtr3base.UserService.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@Service
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserDTO userDTO){
        try {
            if (userDTO == null) {
                throw new RuntimeException("User data cannot be null");
            }

            if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
                throw new RuntimeException("User with this email already exists");
            }
            User user = User.builder()
                    .email(userDTO.getEmail())
                    .followers(Collections.emptyList())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(RoleEnum.USER_ROLE)
                    .username(userDTO.getUsername()).build();
            userRepository.save(user);
            log.info("User " + userDTO.getUsername() + " registered");
        } catch (RuntimeException e) {
            log.error("Operation failed");
        }
    }

    public void update(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        user.setUsername(userDTO.getUsername());
        user.getFollowers().clear();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        for(Follower follower: userDTO.getFollowers()){
            follower.setUser(user);
            user.getFollowers().add(follower);
        }
    }
}
