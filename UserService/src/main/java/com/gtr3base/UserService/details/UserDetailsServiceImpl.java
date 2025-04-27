package com.gtr3base.UserService.details;

import com.gtr3base.UserService.model.User;
import com.gtr3base.UserService.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author hypad on 25.04.2025
 * @project SMedia
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));
    }
}
