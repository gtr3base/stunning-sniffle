package com.gtr3base.PostService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hypad on 28.04.2025
 * @project SMedia
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Long userId;
    private LocalDateTime createdAt, likedAt, commentedAt;
    @ElementCollection
    private Set<Long> likedUserIds = new HashSet<>();
    private int likeCount;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", likeCount=" + likeCount +
                '}';
    }
}
