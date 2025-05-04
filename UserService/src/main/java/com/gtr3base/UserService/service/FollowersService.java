package com.gtr3base.UserService.service;

import com.gtr3base.UserService.dto.FollowerDTO;
import com.gtr3base.UserService.model.Follower;
import com.gtr3base.UserService.model.User;
import com.gtr3base.UserService.repository.FollowerRepository;
import com.gtr3base.UserService.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hypad on 04.05.2025
 * @project SMedia
 */
@Transactional
@Service
@Slf4j
public class FollowersService {
    private final UserRepository userRepository;

    public FollowersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void addFollower(FollowerDTO followerDTO, Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        boolean following = user.getFollowers().stream()
                .anyMatch(f -> f.getUsername().equals(followerDTO.getUsername()));

        if(following){
            throw new IllegalStateException("User is already following this account.");
        }

        Follower follower = Follower
                .builder()
                .username(followerDTO.getUsername())
                .user(user)
                .build();

        user.getFollowers().add(follower);

        userRepository.save(user);
        log.info("Follower added: {}", follower.toString());
    }
}
