package com.reddit.reddit.service;

import com.reddit.reddit.dto.VoteRequest;
import com.reddit.reddit.entity.Post;
import com.reddit.reddit.entity.User;
import com.reddit.reddit.entity.Vote;
import com.reddit.reddit.entity.VoteType;
import com.reddit.reddit.repository.PostRepository;
import com.reddit.reddit.repository.UserRepository;
import com.reddit.reddit.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public String vote(

            VoteRequest request,

            Authentication authentication
    ) {

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User Not Found"
                                )
                        );

        Post post =
                postRepository
                        .findById(
                                request.getPostId()
                        )

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Post Not Found"
                                )
                        );

        Vote existingVote =
                voteRepository
                        .findByUserIdAndPostId(

                                user.getId(),

                                post.getId()
                        )

                        .orElse(null);

        VoteType voteType =
                VoteType.valueOf(
                        request.getType()
                );

        if (existingVote != null) {

            existingVote.setType(
                    voteType
            );

            voteRepository.save(
                    existingVote
            );

            return "Vote Updated";
        }

        Vote vote = new Vote();

        vote.setType(voteType);

        vote.setUser(user);

        vote.setPost(post);

        voteRepository.save(vote);

        return "Vote Added";
    }
}
