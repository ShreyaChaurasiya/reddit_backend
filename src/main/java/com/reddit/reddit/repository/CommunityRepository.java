package com.reddit.reddit.repository;

import com.reddit.reddit.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository

        extends JpaRepository<Community, Long> {

}
