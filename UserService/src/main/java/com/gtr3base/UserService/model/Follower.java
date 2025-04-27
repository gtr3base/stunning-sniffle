package com.gtr3base.UserService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "t_followers")
@Entity
public class Follower {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String f_id;

    private String username;
}
