package com.gtr3base.PostService.controller;

import com.gtr3base.PostService.dto.PostDTO;
import com.gtr3base.PostService.model.Post;
import com.gtr3base.PostService.service.CommunicationService;
import com.gtr3base.PostService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hypad on 28.04.2025
 * @project SMedia
 */
@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostService postService;
    private final CommunicationService communicationService;

    public PostController(PostService postService, CommunicationService communicationService) {
        this.postService = postService;
        this.communicationService = communicationService;
    }

    @GetMapping
    public List<Post> allPosts(){
       return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(String.valueOf(postDTO));
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<String> addLike(@PathVariable Long id){
        try {
            communicationService.addLike(id,0L);
            return ResponseEntity.ok("Like added successfully");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
