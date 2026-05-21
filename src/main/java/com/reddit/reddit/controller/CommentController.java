package com.reddit.reddit.controller;

import com.reddit.reddit.dto.CommentRequest;
import com.reddit.reddit.entity.Comment;
import com.reddit.reddit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/api/comments")

@CrossOrigin("*")

public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public String createComment(

            @RequestBody
            CommentRequest request,

            Authentication authentication
    ) {

        return commentService
                .createComment(
                        request,
                        authentication
                );
    }

    @GetMapping("/{postId}")
    public List<Comment>
    getCommentsByPost(

            @PathVariable
            Long postId
    ) {

        return commentService
                .getCommentsByPost(
                        postId
                );
    }
}
