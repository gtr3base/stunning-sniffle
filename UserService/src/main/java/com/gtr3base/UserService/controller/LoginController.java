package com.gtr3base.UserService.controller;

import com.gtr3base.UserService.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @Cacheable(value = "profile", key = "#me")
    @GetMapping("/{me}")
    public String profile(@PathVariable("me") String me, Model model){
        try {
            model.addAttribute("user", userRepository.findByUsername(me)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found.")));
            return "profile";
        }catch (UsernameNotFoundException ex){
            return "redirect:/login";
        }
    }
}
