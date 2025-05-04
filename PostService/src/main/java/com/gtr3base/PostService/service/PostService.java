package com.gtr3base.PostService.service;

import com.gtr3base.PostService.dto.PostDTO;
import com.gtr3base.PostService.model.Post;
import com.gtr3base.PostService.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hypad on 28.04.2025
 * @project SMedia
 */
@Service
@Transactional
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void createPost(PostDTO postDTO) {
        if(postDTO != null){
            Post post = Post.builder()
                    .content(postDTO.getContent())
                    .createdAt(postDTO.getCreatedAt())
                    .userId(postDTO.getUserId())
                    .comments(postDTO.getComments())
                    .likeCount(postDTO.getLikeCount())
                    .build();

            postRepository.save(post);
            log.info("Post saved: {}", post.toString());
        }
    }
}
