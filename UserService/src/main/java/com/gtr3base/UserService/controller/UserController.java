package com.gtr3base.UserService.controller;

import com.gtr3base.UserService.dto.UserDTO;
import com.gtr3base.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        try {
            userService.register(userDTO);
            return ResponseEntity.ok("User registered successfully");
        }
        catch (RuntimeException e){
            log.error("Operation failed");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        }
    @PutMapping
    public void update(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
    }
}
