package com.komalbandi.kb_blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return this.id;
    }

    @Column(name = "first_name")
    private String first_name;

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "last_name")
    private String last_name;

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Column(name = "email",unique = true)
    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "create_at")
    private Date create_at;

    public Date getCreate_at() {
        return this.create_at;
    }

    public void setCreate_at() {
        this.create_at = new Date();
    }

    @Column(name = "updated_at")
    private Date updated_at;

    public Date getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at() {
        this.updated_at = new Date();
    }
}
