package com.reddit.reddit.service;

import com.reddit.reddit.dto.CommunityRequest;
import com.reddit.reddit.entity.Community;
import com.reddit.reddit.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public String createCommunity(

            CommunityRequest request
    ) {

        Community community =
                new Community();

        community.setName(
                request.getName()
        );

        community.setDescription(
                request.getDescription()
        );

        communityRepository.save(
                community
        );

        return "Community Created";
    }

    public List<Community>
    getAllCommunities() {

        return communityRepository.findAll();
    }

    public Community getCommunityById(
            Long id
    ) {

        return communityRepository
                .findById(id)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Community Not Found"
                        )
                );
    }
}