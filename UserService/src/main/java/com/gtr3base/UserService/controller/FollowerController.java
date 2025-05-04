package com.gtr3base.UserService.controller;

import com.gtr3base.UserService.details.UserDetailsImpl;
import com.gtr3base.UserService.dto.FollowerDTO;
import com.gtr3base.UserService.model.User;
import com.gtr3base.UserService.service.FollowersService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hypad on 04.05.2025
 * @project SMedia
 */
@RestController
@RequestMapping("/followers")
public class FollowerController {
    private final FollowersService followersService;

    public FollowerController(FollowersService followersService) {
        this.followersService = followersService;
    }

    @PostMapping
    public void addFollower(@RequestBody FollowerDTO followerDTO, @AuthenticationPrincipal User user){
        followersService.addFollower(followerDTO,user.getId());
    }
}
