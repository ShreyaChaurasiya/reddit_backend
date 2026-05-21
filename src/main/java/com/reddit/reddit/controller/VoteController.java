package com.reddit.reddit.controller;

import com.reddit.reddit.dto.VoteRequest;
import com.reddit.reddit.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/votes")

@CrossOrigin("*")

public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public String vote(

            @RequestBody
            VoteRequest request,

            Authentication authentication
    ) {

        return voteService.vote(
                request,
                authentication
        );
    }
}
