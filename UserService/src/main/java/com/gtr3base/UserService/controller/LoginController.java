package com.gtr3base.UserService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
