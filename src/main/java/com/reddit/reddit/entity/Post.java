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

    private String imageUrl;

    private int likes = 0;


    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id
    ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(
            String title
    ) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(
            String content
    ) {
        this.content = content;
    }

    public int getLikes() {

        return likes;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public void setLikes(int likes) {

        this.likes = likes;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(
            Community community
    ) {
        this.community = community;
    }
}