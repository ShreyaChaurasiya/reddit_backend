package com.reddit.reddit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String content;

    private String mediaUrl;      // image ya video URL
    private String mediaType;     // "image" ya "video"

    private int likes = 0;
    private String authorUsername;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMediaUrl() { return mediaUrl; }
    public void setMediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public String getAuthorUsername() { return authorUsername; }
    public void setAuthorUsername(String s) { this.authorUsername = s; }

    public Community getCommunity() { return community; }
    public void setCommunity(Community c) { this.community = c; }

    public User getUser() { return user; }
    public void setUser(User u) { this.user = u; }
}