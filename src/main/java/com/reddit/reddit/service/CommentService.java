package com.reddit.reddit.service;

import com.reddit.reddit.dto.CommentRequest;
import com.reddit.reddit.entity.Comment;
import com.reddit.reddit.entity.Post;
import com.reddit.reddit.entity.User;
import com.reddit.reddit.repository.CommentRepository;
import com.reddit.reddit.repository.PostRepository;
import com.reddit.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public String createComment(

            CommentRequest request,

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

        Comment comment =
                new Comment();

        comment.setText(
                request.getText()
        );

        comment.setUser(user);

        comment.setPost(post);

        commentRepository.save(comment);

        return "Comment Added";
    }

    public List<Comment>
    getCommentsByPost(
            Long postId
    ) {

        return commentRepository
                .findByPostId(postId);
    }
}
