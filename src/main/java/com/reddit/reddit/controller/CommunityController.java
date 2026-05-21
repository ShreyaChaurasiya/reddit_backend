package com.reddit.reddit.controller;

import com.reddit.reddit.dto.CommunityRequest;
import com.reddit.reddit.entity.Community;
import com.reddit.reddit.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/community")

@CrossOrigin("*")

public class CommunityController {

    @Autowired
    private CommunityService
            communityService;

    @PostMapping
    public String createCommunity(

            @RequestBody
            CommunityRequest request
    ) {

        return communityService
                .createCommunity(request);
    }

    @GetMapping
    public List<Community>
    getAllCommunities() {

        return communityService
                .getAllCommunities();
    }

    @GetMapping("/{id}")
    public Community getCommunityById(

            @PathVariable
            Long id
    ) {

        return communityService
                .getCommunityById(id);
    }
}