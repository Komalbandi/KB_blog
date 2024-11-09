package com.komalbandi.kb_blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name="BLOG_CATEGORIES")
public class BlogCategories {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    @Setter
    private String name;

    @Column(name="slug")
    @Setter
    private String slug;

    @Column(name="description")
    @Setter
    private String description;

    @Column(name="create_at")
    @Setter
    private String create_at;

    @Column(name="updated_at")
    @Setter
    private String updated_at;

}
