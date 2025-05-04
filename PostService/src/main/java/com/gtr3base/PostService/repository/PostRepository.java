package com.gtr3base.PostService.repository;

import com.gtr3base.PostService.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hypad on 28.04.2025
 * @project SMedia
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
