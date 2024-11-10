package com.komalbandi.kb_blog.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "BLOG_POSTS")
public class BlogPosts {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user_id;

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user) {
        this.user_id = user;
    }

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "slug")
    private String slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(name = "excerpt")
    private String excerpt;

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    @Column(name = "content_html")
    private String content_html;

    public String getContent_html() {
        return content_html;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }
}
