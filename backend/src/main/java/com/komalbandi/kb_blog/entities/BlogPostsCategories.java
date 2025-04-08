package com.komalbandi.kb_blog.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BLOG_POST_CATEGORIES")
public class BlogPostsCategories {
    @Id
    @GeneratedValue
    private Long id;

    public BlogPostsCategories() {
    }

    public Long getId() {
        return this.id;
    }

    @Column(name = "POST_ID")
    private Long postId;

    public Long getPost_id() {
        return postId;
    }

    public void setPost_id(Long post) {
        postId = post;
    }

    @Column(name = "CATEGORY_ID")
    private Long category_id;

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    @Column(name = "CREATED_AT")
    private Date created_at;

    public Date getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at() {
        this.created_at = new Date();
    }

    @Column(name = "UPDATED_AT")
    private Date updated_at;

    public Date getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at() {
        this.updated_at = new Date();
    }

    public BlogPostsCategories(Long post_id, Long category_id) {
        this.category_id = category_id;
        this.postId = post_id;
    }

}
