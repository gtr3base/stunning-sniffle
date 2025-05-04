package com.gtr3base.UserService.dto;

import com.gtr3base.UserService.model.User;
import lombok.Getter;

/**
 * @author hypad on 04.05.2025
 * @project SMedia
 */
@Getter
public class FollowerDTO {
    private User user;

    private String f_id;

    private String username;
}
