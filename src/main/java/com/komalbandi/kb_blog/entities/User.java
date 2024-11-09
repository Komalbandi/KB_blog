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
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name")
    @Setter
    private String first_name;

    @Column(name="last_name")
    @Setter
    private String last_name;

    @Column(name="email")
    @Setter
    private String email;

    @Column(name="password")
    @Setter
    private String password;

    @Column(name="create_at")
    @Setter
    private String create_at;

    @Column(name="updated_at")
    @Setter
    private String updated_at;
}
