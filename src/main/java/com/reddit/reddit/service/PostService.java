package com.reddit.reddit.service;

import com.reddit.reddit.dto.PostRequest;
import com.reddit.reddit.entity.Community;
import com.reddit.reddit.entity.Post;
import com.reddit.reddit.entity.User;
import com.reddit.reddit.repository.CommunityRepository;
import com.reddit.reddit.repository.PostRepository;
import com.reddit.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    public String createPost(PostRequest request, Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Community community = communityRepository
                .findById(request.getCommunityId())
                .orElseThrow(() -> new RuntimeException("Community Not Found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setMediaUrl(request.getMediaUrl());
        post.setMediaType(request.getMediaType());
        post.setCommunity(community);
        post.setUser(user);
        post.setAuthorUsername(user.getUsername());

        postRepository.save(post);
        return "Post Created";
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsByCommunity(Long communityId) {
        return postRepository.findByCommunityId(communityId);
    }
}