package com.gtr3base.PostService.service;

import com.gtr3base.PostService.model.Post;
import com.gtr3base.PostService.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author hypad on 28.04.2025
 * @project SMedia
 */
@Service
public class CommunicationService {
    private final PostRepository postRepository;
    /*private final WebClient webClient;*/

    public CommunicationService(PostRepository postRepository/*, WebClient.Builder webClientBuilder*/) {
        this.postRepository = postRepository;
        /*this.webClient = webClientBuilder.baseUrl("http://localhost:8000").build();*/
    }

    public void addLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found."));

        Set<Long> likedUsers = post.getLikedUserIds();

        if(likedUsers.contains(userId)){
            post.setLikeCount(post.getLikeCount() - 1);
            likedUsers.remove(userId);
        }else{
            post.setLikeCount(post.getLikeCount() + 1);
            likedUsers.add(userId);
        }

        post.setLikedUserIds(likedUsers);
        post.setLikedAt(LocalDateTime.now());
        postRepository.save(post);
    }
}
