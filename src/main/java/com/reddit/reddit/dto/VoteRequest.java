package com.reddit.reddit.dto;

public class VoteRequest {

    private Long postId;

    private String type;

    public VoteRequest() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(
            Long postId
    ) {
        this.postId = postId;
    }

    public String getType() {
        return type;
    }

    public void setType(
            String type
    ) {
        this.type = type;
    }
}
