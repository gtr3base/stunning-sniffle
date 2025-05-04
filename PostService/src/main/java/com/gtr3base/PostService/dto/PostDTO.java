package com.gtr3base.PostService.dto;

import com.gtr3base.PostService.model.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hypad on 28.04.2025
 * @project SMedia
 */
@Getter
public class PostDTO {
    private Long id;

    private String content;
    private Long userId;
    private LocalDateTime createdAt;
    private int likeCount;

    private List<Comment> comments;
}
