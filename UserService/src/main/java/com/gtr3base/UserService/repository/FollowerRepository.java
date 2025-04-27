package com.gtr3base.UserService.repository;

import com.gtr3base.UserService.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
