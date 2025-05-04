package com.gtr3base.UserService.controller;

import com.gtr3base.UserService.dto.UserDTO;
import com.gtr3base.UserService.service.CookieService;
import com.gtr3base.UserService.service.UserService;
import com.gtr3base.UserService.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;
    private final CookieService cookieService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, CookieService cookieService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.cookieService = cookieService;
        this.jwtUtil = jwtUtil;
    }

    @CachePut(value = "profile", key = "#userDTO.username")
    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        userService.register(userDTO);
        String token = jwtUtil.generateToken(userDTO.getUsername());

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("me", userDTO.getUsername());

        log.info("Map, {}", map);
        cookieService.createCookie(map, response);

        return ResponseEntity.ok(token);
    }

    @CacheEvict(value = "profile", key = "#userDTO.username")
    @PutMapping
    public ResponseEntity<String> update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return ResponseEntity.ok("User updated");
    }
}
