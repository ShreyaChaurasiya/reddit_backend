package com.reddit.reddit.repository;

import com.reddit.reddit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository

        extends JpaRepository<Post, Long> {

    List<Post> findByCommunityId(
            Long communityId
    );
}