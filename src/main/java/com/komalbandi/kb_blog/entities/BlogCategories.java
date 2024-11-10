package com.komalbandi.kb_blog.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "BLOG_CATEGORIES")
public class BlogCategories {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "slug")
    private String slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "create_at")
    private Date create_at;

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at() {
        this.create_at = new Date();
    }

    @Column(name = "updated_at")
    private Date updated_at;

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at() {
        this.updated_at = new Date();
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "BLOG_POST_CATEGORIES",
            joinColumns = {@JoinColumn(name = "POSTS_ID")},
            inverseForeignKey = @ForeignKey(name = "CATEGORIES_ID")
    )
    private Set<BlogCategories> categories;

    public Set<BlogCategories> getCategories() {
        return categories;
    }

}
