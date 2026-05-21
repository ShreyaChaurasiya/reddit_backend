package com.reddit.reddit.controller;

import com.reddit.reddit.dto.PostRequest;
import com.reddit.reddit.entity.Post;
import com.reddit.reddit.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/api/posts")

@CrossOrigin("*")

public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public String createPost(

            @RequestBody
            PostRequest request
    ) {

        return postService
                .createPost(request);
    }

    @GetMapping
    public List<Post> getAllPosts() {

        return postService
                .getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(

            @PathVariable
            Long id
    ) {

        return postService
                .getPostById(id);
    }

    @PutMapping("/like/{id}")
    public Post likePost(

            @PathVariable
            Long id
    ) {

        Post post =
                postService.getPostById(id);

        post.setLikes(
                post.getLikes() + 1
        );

        return postService.savePost(post);
    }

    @GetMapping("/community/{communityId}")
    public List<Post>
    getPostsByCommunity(

            @PathVariable
            Long communityId
    ) {

        return postService
                .getPostsByCommunity(
                        communityId
                );
    }

}
