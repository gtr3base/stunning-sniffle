package com.gtr3base.UserService.dto;

import com.gtr3base.UserService.enums.RoleEnum;
import com.gtr3base.UserService.model.Follower;
import lombok.Getter;

import java.util.List;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@Getter
public class UserDTO {
    private List<Follower> followers;

    private String username;
    private String password;
    private String email;
    private RoleEnum role;
}
