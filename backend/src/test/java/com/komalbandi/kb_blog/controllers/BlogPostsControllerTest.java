package com.komalbandi.kb_blog.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

class BlogPostsControllerTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate;

    public BlogPostsControllerTest() {
        this.restTemplate = new TestRestTemplate();
    }

    @Test
    void createBlogPosts() {
    }
}